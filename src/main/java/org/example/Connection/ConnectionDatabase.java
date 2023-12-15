package org.example.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    public Connection connection;

    public ConnectionDatabase() throws SQLException {
        this.connection = DriverManager.getConnection(
                System.getenv("DB_URL"),
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD")
                );
   }

    public Connection getConnection() {
        return this.connection;
    }
}