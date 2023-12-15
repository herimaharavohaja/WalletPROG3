package org.example.repository;

import org.example.Connection.ConnectionDatabase;
import org.example.Models.TransferHistory;
import org.example.Repositor.InterfaceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TransferHistoryRepository implements InterfaceRepository<TransferHistory> {

    @Override
    public TransferHistory findById(int toFind) {
        return null;
    }

    @Override
    public List<TransferHistory> findAll() {
        List<TransferHistory> transferHistories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDatabase.getConnection();
            String SELECT_ALL_QUERY = "SELECT * FROM transfer";
            statement = connection.prepareStatement(SELECT_ALL_QUERY);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TransferHistory transferHistory = new TransferHistory();
                transferHistories.add(transferHistory);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve all transfer History: " + e.getMessage());
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return transferHistories;
    }

    @Override
    public List<TransferHistory> saveAll(List<TransferHistory> toSave) {
        List<TransferHistory> savedTransfers = new ArrayList<>();
        for (TransferHistory transferHistory : toSave) {
            TransferHistory savedTransfer = this.save(transferHistory);
            savedTransfers.add(savedTransfer);
        }
        return savedTransfers;
    }

    @Override
    public TransferHistory save(TransferHistory toSave) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDatabase.getConnection();
            String SAVE_QUERY;

            if (toSave.getTransferHistoryId() == null) {
                SAVE_QUERY = "INSERT INTO transfer (debit_transaction_id, credit_transaction_id, transfer_date) "
                        + "VALUES(?, ?, ?) RETURNING *";
                statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
                // Set parameters for insertion
                statement.setString(1, String.valueOf(toSave.getDebitTransaction()));
                statement.setString(2, String.valueOf(toSave.getCreditTransaction()));
                statement.setTimestamp(3, Timestamp.valueOf(toSave.getTransferDate()));
                statement.executeUpdate();

                resultSet = statement.getGeneratedKeys();
            } else {
                SAVE_QUERY = "UPDATE transfer "
                        + "SET debit_transaction_id = ?, credit_transaction_id = ?, transfer_date = ? "
                        + "WHERE transfer_id = ? RETURNING *";
                statement = connection.prepareStatement(SAVE_QUERY);
                // Set parameters for update
                statement.setString(1, String.valueOf(toSave.getDebitTransaction()));
                statement.setString(2, String.valueOf(toSave.getCreditTransaction()));
                statement.setTimestamp(3, Timestamp.valueOf(toSave.getTransferDate()));
                statement.setLong(4, toSave.getTransferHistoryId());
                statement.executeUpdate();
            }

            if (resultSet != null && resultSet.next()) {
                toSave.setTransferHistoryId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save transfer History: " + e.getMessage());
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return toSave;
    }

    @Override
    public void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close resources: " + e.getMessage());
        }
    }
}
