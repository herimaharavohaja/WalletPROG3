package org.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                Credentials.DATABASE_URL,
                Credentials.DATABASE_USER,
                Credentials.DATABASE_PASSWORD
                );
   }
}