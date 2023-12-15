package org.example.Repository;

import org.example.Connection.ConnectionDatabase;
import org.example.Model.CurrencyValue;

import java.sql.*;

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
}
