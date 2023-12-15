package org.example.repository;
import java.sql.*;
import java.util.ArrayList;


public class TransactionRepository implements InterfaceRepository<Transaction> {

    @Override
    public Transaction findById(int toFind) {
        // Implémentez la logique pour trouver une transaction par son ID dans la base de données
        // Utilisez PreparedStatement pour exécuter une requête sécurisée
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = ConnectionDatabase.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                // Initialisez les propriétés de la transaction à partir des données ResultSet
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve all transactions : " + e.getMessage());
        }
        return transactions;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        List<Transaction> savedTransactions = new ArrayList<>();

        for (Transaction transaction : toSave) {
            Transaction savedTransaction = this.save(transaction);
            savedTransactions.add(savedTransaction);
        }

        return savedTransactions;
    }

    @Override
    public Transaction save(Transaction toSave) {
        try (Connection connection = ConnectionDatabase.getConnection()) {
            String saveQuery;
            if (toSave.getTransactionId() == null) {
                saveQuery = "INSERT INTO transactions (label, amount, transaction_date_time, account_id, transaction_type) " +
                        "VALUES (?, ?, ?, ?, ?::transaction_type) RETURNING *";
                try (PreparedStatement statement = connection.prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS)) {
                    // Paramétrisez les valeurs pour l'insertion
                    statement.executeUpdate();
                    // Récupérez les clés générées si nécessaire
                }
            } else {
                saveQuery = "UPDATE transactions SET label = ?, amount = ?, transaction_date_time = ?, " +
                        "account_id = ?, transaction_type = ?::account_type WHERE transaction_id = ? RETURNING *";
                try (PreparedStatement statement = connection.prepareStatement(saveQuery)) {
                    // Paramétrisez les valeurs pour la mise à jour
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save transaction : " + e.getMessage());
        }
        return toSave;
    }

}
