package org.example.thesis_management_system.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=Intellij_App;user=sa;password=NewPassword123;encrypt=false;";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "NewPassword123";

    static {
        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load SQL Server JDBC driver", e);
        }
    }

    // Get the database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }


}
