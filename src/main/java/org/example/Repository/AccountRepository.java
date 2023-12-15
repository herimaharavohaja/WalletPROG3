package org.example.Repository;

import org.example.Connection.ConnectionDatabase;
import org.example.Model.Account;
import org.example.Model.Devise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private Connection connection;

    public AccountRepository() throws SQLException {
        this.connection = new ConnectionDatabase().getConnection();
    }

    public void create(Account account) throws SQLException {
        String query = "INSERT INTO Compte (Nom, Solde, Date_maj_solde, Devise_ID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, account.getNom());
            statement.setBigDecimal(2, account.getSolde());
            statement.setTimestamp(3, Timestamp.valueOf(account.getDateMajSolde()));
            statement.setInt(4, account.getDevise().getId());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                account.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating Compte failed, no ID obtained.");
            }
        }
    }
    public void update(Account account) throws SQLException {
        String query = "UPDATE Compte SET Nom = ?, Solde = ?, Date_maj_solde = ?, Devise_ID = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getNom());
            statement.setBigDecimal(2, account.getSolde());
            statement.setTimestamp(3, Timestamp.valueOf(account.getDateMajSolde()));
            statement.setInt(4, account.getDevise().getId());
            statement.setInt(5, account.getId());

            statement.executeUpdate();
        }
    }


    public Account getById(int id) throws SQLException {
        String query = "SELECT * FROM Compte WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCompte(resultSet);
            }
        }
        return null;
    }

    public List<Account> getAll() throws SQLException {
        List<Account> comptes = new ArrayList<>();
        String query = "SELECT * FROM Compte";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                comptes.add(mapResultSetToCompte(resultSet));
            }
        }
        return comptes;
    }

    public List<Account> getAllPagination(int offset, int limit) throws SQLException {
        List<Account> comptes = new ArrayList<>();
        String query = "SELECT * FROM Compte LIMIT ? OFFSET ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comptes.add(mapResultSetToCompte(resultSet));
            }
        }
        return comptes;
    }


    private Account mapResultSetToCompte(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("ID"));
        account.setNom(resultSet.getString("Nom"));
        account.setSolde(resultSet.getBigDecimal("Solde"));
        account.setDateMajSolde(resultSet.getTimestamp("Date_maj_solde").toLocalDateTime());

        Devise devise = new Devise();
        devise.setId(resultSet.getInt("Devise_ID"));
        account.setDevise(devise);
        return account;
    }

}
