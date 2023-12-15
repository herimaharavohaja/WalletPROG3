package org.example.repository;

import org.example.Connection.ConnectionDatabase;
import org.example.Models.Account;
import org.example.Models.Currency;
import org.example.Repositor.InterfaceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRepository implements InterfaceRepository<Currency> {

    @Override
    public Account findById(int toFind) {
        return null;
    }
    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();

        Connection connection = ConnectionDatabase.getConnection();
        String SELECT_ALL_QUERY = "SELECT * FROM currency";

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Currency currency = new Currency();
                currency.setCurrencyId(resultSet.getLong("currency_id"));
                currency.setCurrencyName(resultSet.getString("currency_name"));
                currency.setCurrencyCode(resultSet.getString("currency_code"));

                currencies.add(currency);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve all currencies : " + e.getMessage());
        } finally {
            closeResources(connection, null, null);
        }
        return currencies;
    }

    @Override
    public List<Currency> saveAll(List<Currency> toSave) {
        List<Currency> savedCurrencies = new ArrayList<>();

        for (Currency currency : toSave) {
            Currency savedCurrency = this.save(currency);
            savedCurrencies.add(savedCurrency);
        }

        return savedCurrencies;
    }

    @Override
    public Currency save(Currency toSave) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDatabase.getConnection();
            String SAVE_QUERY;

            if (toSave.getCurrencyId() == null) {
                SAVE_QUERY = "INSERT INTO currency (currency_name, currency_code) VALUES (?, ?) RETURNING *";
                statement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, toSave.getCurrencyName());
                statement.setString(2, toSave.getCurrencyCode());
                statement.executeUpdate();

                resultSet = statement.getGeneratedKeys();
            } else {
                SAVE_QUERY = "UPDATE currency SET currency_name = ?, currency_code = ? WHERE currency_id = ? RETURNING *";
                statement = connection.prepareStatement(SAVE_QUERY);
                statement.setString(1, toSave.getCurrencyName());
                statement.setString(2, toSave.getCurrencyCode());
                statement.setLong(3, toSave.getCurrencyId());
                statement.executeUpdate();
            }

            if (resultSet != null && resultSet.next()) {
                toSave.setCurrencyId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save currency: " + e.getMessage());
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
