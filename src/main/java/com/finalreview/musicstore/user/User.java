package com.finalreview.musicstore.user;

// Super Class for any people in the system
public class User {
    private int user_id;
    private String userName;
    private String password;
    private String email;
    private String role; // e.g., "customer", "employee"

    public User(int userId, String username, String password, String email, String role) {
        this.user_id = userId;
        this.userName = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String username, String password, String email, String role) {
        this.userName = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userId) {
        this.user_id = userId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + user_id +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
