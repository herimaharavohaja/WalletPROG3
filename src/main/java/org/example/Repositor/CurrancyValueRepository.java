package org.example.Repositor;

import org.example.Connection.ConnectionDatabase;
import org.example.Models.Account;
import org.example.Models.CurrencyValue;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrancyValueRepository implements InterfaceRepository<CurrencyValue> {
    @Override
    public Account findById(int toFind) {
        return null;
    }

    @Override
    public List<CurrencyValue> findAll() {
        return null;
    }

    @Override
    public List<CurrencyValue> saveAll(List<CurrencyValue> toSave) {
        return null;
    }

    @Override
    public CurrencyValue save(CurrencyValue toSave) {
        return null;
    }


    @Override
    public void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {

    }

    public double getConversionRate(Long sourceCurrencyId, Long destinationCurrencyId, LocalDate date) {
        Connection connection = ConnectionDatabase.getConnection();
        String QUERY = "SELECT value " +
                "FROM currency_value " +
                "WHERE currency_value_id = ? AND destination_currency_id = ? AND effective_date <= ? " +
                "ORDER BY effective_date DESC LIMIT 1";

        try (
                PreparedStatement statement = connection.prepareStatement(QUERY)) {
            statement.setLong(1, sourceCurrencyId);
            statement.setLong(2, destinationCurrencyId);
            statement.setDate(3, java.sql.Date.valueOf(date));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("amount");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch conversion rate: " + e.getMessage());
        }

        throw new RuntimeException("Conversion rate not found for given currencies and date");
    }

    public List<CurrencyValue> retrieveCurrencyValuesForDay(LocalDate date) {
        Connection connection = ConnectionDatabase.getConnection();
        List<CurrencyValue> currencyValues = new ArrayList<>();

        String QUERY = "SELECT * FROM currency_value WHERE DATE(effective_date) = ?";

        try (PreparedStatement statement = connection.prepareStatement(QUERY)) {
            statement.setDate(1, java.sql.Date.valueOf(date));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long currencyValueId = resultSet.getLong("currency_value_id");
                    int sourceCurrencyId = resultSet.getInt("source_currency_id");
                    int destinationCurrencyId = resultSet.getInt("destination_currency_id");
                    double amount = resultSet.getDouble("amount");
                    LocalDate effectiveDate = resultSet.getDate("effective_date").toLocalDate();

                    CurrencyValue currencyValue = new CurrencyValue(
                            currencyValueId,
                            sourceCurrencyId,
                            destinationCurrencyId,
                            amount,
                            effectiveDate);
                    currencyValues.add(currencyValue);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve currency value for the day : " + e.getMessage());
        }

        return currencyValues;
    }
}
