package org.example.thesis_management_system.Models;

public abstract class User {
    private int id;
    private String username;
    private String password; // Store a hashed password in real implementations
    private String role; // Allowed values: "student", "supervisor", "admin"

    // Constructor
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        setPassword(password); // Apply validation or hashing
        setRole(role); // Ensure only valid roles
    }

    public User() {

    }

    public User(int userId, String name, String email) {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 15) {
            throw new IllegalArgumentException("Password must be at least 15 characters long");
        }
        // Placeholder for hashing logic (if needed)
        this.password = password;
    }
    public boolean isSupervisor() {
        return "supervisor".equalsIgnoreCase(role); // Check if the user is a supervisor
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (!role.equals("student") && !role.equals("supervisor") && !role.equals("admin")) {
            throw new IllegalArgumentException("Role must be student, supervisor, or admin");
        }
        this.role = role;
    }

    // Behavior
    public void login() {
        System.out.println(username + " has logged in as " + role);
    }

    public void logout() {
        System.out.println(username + " has logged out.");
    }

    // Override toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    // Override equals and hashCode for proper comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public abstract void login(String username, String password);
}

