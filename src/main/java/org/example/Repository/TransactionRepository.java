package org.example.Repository;

import org.example.Connection.ConnectionDatabase;
import org.example.Model.Account;
import org.example.Model.Transaction;
import org.example.Model.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private Connection connection;

    public TransactionRepository() throws SQLException {
        this.connection = new ConnectionDatabase().getConnection();
    }

    public void create(Transaction transaction) throws SQLException {
        String query = "INSERT INTO Transaction (Label, Montant, Date_heure, Type, Compte_ID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, transaction.getLabel());
            statement.setBigDecimal(2, transaction.getMontant());
            statement.setTimestamp(3, Timestamp.valueOf(transaction.getDateHeure()));
            statement.setString(4, transaction.getTransactionType().toString());
            statement.setInt(5, transaction.getAccount().getId());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                transaction.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating Transaction failed, no ID obtained.");
            }
        }
    }
    public void update(Transaction transaction) throws SQLException {
        String query = "UPDATE Transaction SET Label = ?, Montant = ?, Date_heure = ?, Type = ?, Compte_ID = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, transaction.getLabel());
            statement.setBigDecimal(2, transaction.getMontant());
            statement.setTimestamp(3, Timestamp.valueOf(transaction.getDateHeure()));
            statement.setString(4, transaction.getTransactionType().toString());
            statement.setInt(5, transaction.getAccount().getId());
            statement.setInt(6, transaction.getId());

            statement.executeUpdate();
        }
    }
    public Transaction getById(int id) throws SQLException {
        String query = "SELECT * FROM Transaction WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToTransaction(resultSet);
            }
        }
        return null;
    }
    public List<Transaction> getAll() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM Transaction";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                transactions.add(mapResultSetToTransaction(resultSet));
            }
        }
        return transactions;
    }
    public List<Transaction> getAllPagination(int pageNumber) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        int limit = 10;
        int offset = (pageNumber - 1) * limit;

        String query = "SELECT * FROM Transaction LIMIT ? OFFSET ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactions.add(mapResultSetToTransaction(resultSet));
            }
        }
        return transactions;
    }
    private Transaction mapResultSetToTransaction(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("ID"));
        transaction.setLabel(resultSet.getString("Label"));
        transaction.setMontant(resultSet.getBigDecimal("Montant"));
        transaction.setDateHeure(resultSet.getTimestamp("Date_heure").toLocalDateTime());
        transaction.setTransactionType(TransactionType.valueOf(resultSet.getString("Type")));

        Account account = new Account();
        account.setId(resultSet.getInt("Compte_ID"));
        transaction.setAccount(account);

        return transaction;
    }
}
