package org.example.Repository;

import org.example.Connection.ConnectionDatabase;
import org.example.Model.TransferHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferHistoryRepository {
    private Connection connection;

    public TransferHistoryRepository() throws SQLException {
        this.connection = new ConnectionDatabase().getConnection();
    }
    public void create(TransferHistory transferHistory) throws SQLException {
        String query = "INSERT INTO TransferHistory (Transfer_ID, Date_transfert) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, transferHistory.getTransferId());
            statement.setTimestamp(2, Timestamp.valueOf(transferHistory.getDateTransfert()));

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                transferHistory.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating Transfer History failed, no ID obtained.");
            }
        }
    }
    public void update(TransferHistory transferHistory) throws SQLException {
        String query = "UPDATE TransferHistory SET Transfer_ID = ?, Date_transfert = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transferHistory.getTransferId());
            statement.setTimestamp(2, Timestamp.valueOf(transferHistory.getDateTransfert()));
            statement.setInt(3, transferHistory.getId());

            statement.executeUpdate();
        }
    }
    public TransferHistory getById(int id) throws SQLException {
        String query = "SELECT * FROM TransferHistory WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToTransferHistory(resultSet);
            }
        }
        return null;
    }
    public List<TransferHistory> getAll() throws SQLException {
        List<TransferHistory> transferHistories = new ArrayList<>();
        String query = "SELECT * FROM TransferHistory";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                transferHistories.add(mapResultSetToTransferHistory(resultSet));
            }
        }
        return transferHistories;
    }
    public List<TransferHistory> getAllPagination(int pageNumber, int pageSize) throws SQLException {
        List<TransferHistory> transferHistories = new ArrayList<>();
        int limit = 10;
        int offset = (pageNumber - 1) * pageSize;

        String query = "SELECT * FROM TransferHistory LIMIT ? OFFSET ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transferHistories.add(mapResultSetToTransferHistory(resultSet));
            }
        }
        return transferHistories;
    }
    private TransferHistory mapResultSetToTransferHistory(ResultSet resultSet) throws SQLException {
        TransferHistory history = new TransferHistory();
        history.setId(resultSet.getInt("ID"));
        history.setTransferId(resultSet.getInt("Transfer_ID"));
        history.setDateTransfert(resultSet.getTimestamp("Date_transfert").toLocalDateTime());
        return history;
    }
}
