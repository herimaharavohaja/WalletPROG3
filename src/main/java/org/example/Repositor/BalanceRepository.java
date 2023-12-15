package org.example.Repositor;

import org.example.Connection.ConnectionDatabase;
import org.example.Models.Account;
import org.example.Models.Balance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BalanceRepository implements InterfaceRepository<Balance>{
        @Override
        public Account findById(int toFind) {
            return null;
        }

        @Override
        public List<Balance> findAll() {
            return null;
        }

        @Override
        public List<Balance> saveAll(List<Balance> toSave) {
            List<Balance> savedBalances = new ArrayList<>();

            for (Balance balance : toSave) {
                Balance savedBalance = this.save(balance);
                savedBalances.add(savedBalance);
            }

            return savedBalances;
        }

        @Override
        public Balance save(Balance toSave) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                connection = ConnectionDatabase.getConnection();
                String SAVE_QUERY;

                if (toSave.getBalanceId() == null) {
                    SAVE_QUERY =
                            "INSERT INTO balance (balance_date_time, amount, account_id) "
                                    + "VALUES(?, ?, ?) RETURNING *";
                    statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
                    statement.setTimestamp(1, Timestamp.valueOf(toSave.getBalanceDateTime()));
                    statement.setDouble(2, Double.parseDouble(String.valueOf(toSave.getAmount())));
                    statement.setInt(3, Math.toIntExact(toSave.getAccount().getAccountId()));
                    statement.executeUpdate();

                    resultSet = statement.getGeneratedKeys();
                } else {
                    SAVE_QUERY =
                            "UPDATE balance "
                                    + "SET balance_date_time = ?, amount = ?, account_id = ? "
                                    + "WHERE balance_id = ? RETURNING *";
                    statement = connection.prepareStatement(SAVE_QUERY);
                    statement.setTimestamp(1, Timestamp.valueOf(toSave.getBalanceDateTime()));
                    statement.setDouble(2, toSave.getAmount());
                    statement.setInt(3, Math.toIntExact(toSave.getAccount().getAccountId()));
                    statement.setLong(4, toSave.getBalanceId());
                    statement.executeUpdate();
                }

                if (resultSet != null && resultSet.next()) {
                    toSave.setBalanceId(resultSet.getLong(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to save balance: " + e.getMessage());
            } finally {
                closeResources(connection, statement, resultSet);
            }
            return toSave;
        }


        @Override
        public void closeResources(
                Connection connection, PreparedStatement statement, ResultSet resultSet) {
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


