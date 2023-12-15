package org.example.Repositor;

import org.example.Connection.ConnectionDatabase;
import org.example.Models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository  implements InterfaceRepository <Account> {
        @Override
        public Account findById(int toFind) {
            String SELECT_BY_ID_QUERY = "SELECT * FROM account WHERE account_id = ?";
            Connection connection = ConnectionDatabase.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
                statement.setLong(1, toFind);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return new Account();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        public List<Account> findAll() {
            List<Account> accounts = new ArrayList<>();

            Connection connection = ConnectionDatabase.getConnection();
            String SELECT_ALL_QUERY = "SELECT * FROM account";

            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Account account = new Account();
                    accounts.add(account);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to retrieve all account : " + e.getMessage());
            } finally {
                closeResources(connection, null, null);
            }
            return accounts;
        }


        @Override
        public List<Account> saveAll(List<Account> toSave) {
            List<Account> savedAccounts = new ArrayList<>();

            for (Account account : toSave) {
                Account savedAccount = this.save(account);
                savedAccounts.add(savedAccount);
            }

            return savedAccounts;
        }

        @Override
        public Account save(Account toSave) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                connection = ConnectionDatabase.getConnection();
                String SAVE_QUERY;

                if (toSave.getAccountId() == null) {
                    SAVE_QUERY =
                            "INSERT INTO account (account_name, account_type, currency_id) "
                                    + "VALUES(?, CAST(? AS account_type), ?) RETURNING *";
                    statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, toSave.getAccountName());
                    statement.setString(2, String.valueOf(toSave.getAccountType()));
                    statement.executeUpdate();

                    resultSet = statement.getGeneratedKeys();
                } else {
                    SAVE_QUERY =
                            "UPDATE account "
                                    + "SET account_name = ?, account_type = CAST(? AS account_type), currency_id = ? "
                                    + "WHERE account_id = ? RETURNING *";
                    statement = connection.prepareStatement(SAVE_QUERY);
                    statement.setString(1, toSave.getAccountName());
                    statement.setString(2, String.valueOf(toSave.getAccountType()));
                    statement.setLong(4, toSave.getAccountId());
                    statement.executeUpdate();
                }

                if (resultSet != null && resultSet.next()) {
                    toSave.setAccountId(resultSet.getLong(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to save account: " + e.getMessage());
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
