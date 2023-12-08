package org.example.Repository;

import org.example.Connection.ConnectionDatabase;
import org.example.Model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
        private static final String SELECT_ALL_QUERY = "SELECT * FROM Account";
        private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Account WHERE id = ?";
        private static final String UPDATE_QUERY = "UPDATE Account SET nom = ?, solde = ?, dateDerniereMiseAJour = ? WHERE id = ?";
        private static final String CREATE_QUERY = "INSERT INTO Account (nom, solde, dateDerniereMiseAJour) VALUES (?, ?, ?)";


    public List<List<Account>> getAccountsPagination(Date dateHeure) {
        List<List<Account>> pages = new ArrayList<>();

        final int pageSize = 10;
        int pageNumber = 0;

        try (Connection connection = ConnectionDatabase.createConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            resultSet.last();
            int totalRows = resultSet.getRow();
            resultSet.beforeFirst();

            int pageCount = (int) Math.ceil((double) totalRows / pageSize);

            while (pageNumber < pageCount) {
                List<Account> currentPage = new ArrayList<>();
                int count = 0;

                while (resultSet.next() && count < pageSize) {
                    Account account = new Account();
                    double soldeA = account.getSolde();
                    account.setSolde(soldeA);
                    currentPage.add(account);
                    count++;
                }

                pages.add(currentPage);
                pageNumber++;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pages;
    }

    public List<Double> getBalanceHistoryForAccountInTimeInterval(int accountId, Date startDate, Date endDate) {
        List<Double> balanceHistory = new ArrayList<>();

        try (Connection connection = ConnectionDatabase.createConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT solde FROM Transaction WHERE accountId = ? AND dateTransaction BETWEEN ? AND ? ORDER BY dateTransaction")) {

            statement.setInt(1, accountId);
            statement.setTimestamp(2, new Timestamp(startDate.getTime()));
            statement.setTimestamp(3, new Timestamp(endDate.getTime()));

            ResultSet resultSet = statement.executeQuery();

            double currentBalance = 0.0;

            while (resultSet.next()) {
                double transactionAmount = resultSet.getDouble("solde");
                currentBalance += transactionAmount;
                balanceHistory.add(currentBalance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balanceHistory;
    }

    public Account getAccountById(int id) {
            Account account = null;

            try (Connection connection = ConnectionDatabase.createConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {

                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return account;
        }

        public void updateAccount(Account account) {
            try (Connection connection = ConnectionDatabase.createConnection();
                 PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

                statement.setString(1, account.getNom());
                statement.setDouble(2, account.getSolde());
                statement.setDate(3, new java.sql.Date(account.getDateDerniereMiseAJour().getTime()));
                statement.setInt(4, account.getId());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Mise à jour réussie pour le compte avec l'ID : " + account.getId());
                } else {
                    System.out.println("Aucune mise à jour effectuée pour le compte avec l'ID : " + account.getId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void createAccount(Account account) {
            try (Connection connection = ConnectionDatabase.createConnection();
                 PreparedStatement statement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, account.getNom());
                statement.setDouble(2, account.getSolde());
                statement.setDate(3, new java.sql.Date(account.getDateDerniereMiseAJour().getTime()));

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        account.setId(newId);
                        System.out.println("Nouveau compte ajouté avec l'ID : " + newId);
                    }
                } else {
                    System.out.println("Échec de l'ajout du nouveau compte.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
