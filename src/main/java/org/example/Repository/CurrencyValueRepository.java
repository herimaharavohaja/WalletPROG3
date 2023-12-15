package org.example.Repository;

import org.example.Connection.ConnectionDatabase;
import org.example.Model.CurrencyValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyValueRepository {
    private Connection connection;

    public CurrencyValueRepository() throws SQLException {
        this.connection = new ConnectionDatabase().getConnection();
    }

    public void create(CurrencyValue currencyValue) throws SQLException {
        String query = "INSERT INTO CurrencyValue (DeviseSource_ID, DeviseDestination_ID, Montant, DateEffet) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, currencyValue.getDeviseSourceId());
            statement.setInt(2, currencyValue.getDeviseDestinationId());
            statement.setBigDecimal(3, currencyValue.getMontant());
            statement.setTimestamp(4, Timestamp.valueOf(currencyValue.getDateEffet()));

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                currencyValue.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating CurrencyValue failed, no ID obtained.");
            }
        }
    }
    public void update(CurrencyValue currencyValue) throws SQLException {
        String query = "UPDATE CurrencyValue SET DeviseSource_ID = ?, DeviseDestination_ID = ?, Montant = ?, DateEffet = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, currencyValue.getDeviseSourceId());
            statement.setInt(2, currencyValue.getDeviseDestinationId());
            statement.setBigDecimal(3, currencyValue.getMontant());
            statement.setTimestamp(4, Timestamp.valueOf(currencyValue.getDateEffet()));
            statement.setInt(5, currencyValue.getId());

            statement.executeUpdate();
        }
    }

    public CurrencyValue getById(int id) throws SQLException {
        String query = "SELECT * FROM CurrencyValue WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCurrencyValue(resultSet);
            }
        }
        return null;
    }

    public List<CurrencyValue> getAll() throws SQLException {
        List<CurrencyValue> currencyValues = new ArrayList<>();
        String query = "SELECT * FROM CurrencyValue";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                currencyValues.add(mapResultSetToCurrencyValue(resultSet));
            }
        }
        return currencyValues;
    }
    public List<CurrencyValue> getAllPagination(int pageNumber) throws SQLException {
        List<CurrencyValue> currencyValues = new ArrayList<>();
        int limit = 10;
        int offset = (pageNumber - 1) * limit;

        String query = "SELECT * FROM CurrencyValue LIMIT ? OFFSET ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                currencyValues.add(mapResultSetToCurrencyValue(resultSet));
            }
        }
        return currencyValues;
    }
    private CurrencyValue mapResultSetToCurrencyValue(ResultSet resultSet) throws SQLException {
        CurrencyValue currencyValue = new CurrencyValue();
        currencyValue.setId(resultSet.getInt("ID"));
        currencyValue.setDeviseSourceId(resultSet.getInt("DeviseSource_ID"));
        currencyValue.setDeviseDestinationId(resultSet.getInt("DeviseDestination_ID"));
        currencyValue.setMontant(resultSet.getBigDecimal("Montant"));
        currencyValue.setDateEffet(resultSet.getTimestamp("DateEffet").toLocalDateTime());
        return currencyValue;
    }

}
