package org.example.Repository;
import org.example.Connection.ConnectionDatabase;
import org.example.Model.Devise;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviseRepository {
    private Connection connection;

    public DeviseRepository() throws SQLException {
        this.connection = new ConnectionDatabase().getConnection();
    }

    public void create(Devise devise) throws SQLException {
        String query = "INSERT INTO Devise (Nom, Code) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, devise.getNom());
            statement.setString(2, devise.getCode());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                devise.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating Devise failed, no ID obtained.");
            }
        }
    }

    public void update(Devise devise) throws SQLException {
        String query = "UPDATE Devise SET Nom = ?, Code = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, devise.getNom());
            statement.setString(2, devise.getCode());
            statement.setInt(3, devise.getId());

            statement.executeUpdate();
        }
    }

    public List<Devise> getAll() throws SQLException {
        List<Devise> devises = new ArrayList<>();
        String query = "SELECT * FROM Devise";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                devises.add(mapResultSetToDevise(resultSet));
            }
        }
        return devises;
    }

    public List<Devise> getAllPagination(int pageNumber) throws SQLException {
        List<Devise> devises = new ArrayList<>();
        int limit = 10;
        int offset = (pageNumber - 1) * limit;

        String query = "SELECT * FROM Devise LIMIT ? OFFSET ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                devises.add(mapResultSetToDevise(resultSet));
            }
        }
        return devises;
    }

    public Devise getById(int id) throws SQLException {
        String query = "SELECT * FROM Devise WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToDevise(resultSet);
            }
        }
        return null;
    }

    private Devise mapResultSetToDevise(ResultSet resultSet) throws SQLException {
        Devise devise = new Devise();
        devise.setId(resultSet.getInt("ID"));
        devise.setNom(resultSet.getString("Nom"));
        devise.setCode(resultSet.getString("Code"));
        return devise;
    }
}
