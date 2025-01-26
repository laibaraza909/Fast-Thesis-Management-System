package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.User;

import java.sql.*;
import java.util.*;

public class UserDAO {
    private Connection connection;

    // Constructor to initialize connection
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("user_type");

            // Create a User object based on the retrieved data
            User user = new User(id, username, password, role) {
                @Override
                public void login(String username, String password) {
                    // Custom login logic (could be used for validation)
                }
            };
            users.add(user);
        }
        return users;
    }

    // Create a new user
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (username, password, user_type) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRole());
        preparedStatement.executeUpdate();
    }

    // Delete a user by ID
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();
    }

    // Update a user's details
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET username = ?, password = ?, user_type = ? WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRole());
        preparedStatement.setInt(4, user.getId());
        preparedStatement.executeUpdate();
    }

    // Find user by ID
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("user_type");

            return new User(userId, username, password, role) {
                @Override
                public void login(String username, String password) {
                    // Custom login logic can be added here
                }
            };
        }
        return null; // Return null if no user found
    }


    // Fetch evaluator details based on evaluatorId
    public User getEvaluatorById(int evaluatorId) throws SQLException {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, evaluatorId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    return new User(userId, name, email) {
                        @Override
                        public void login(String username, String password) {

                        }
                    };  // Return the evaluator's user object
                } else {
                    return null;  // If no evaluator is found with the given ID
                }
            }
        }
    }
}
