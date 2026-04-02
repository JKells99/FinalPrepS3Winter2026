package com.finalreview.musicstore.user;

import com.finalreview.musicstore.database.DBConnection;

import java.sql.SQLException;

public class UserDAO {

    public void saveNewUserToDb(User user) {
        String sql = "INSERT INTO users(username, password, email, role) VALUES (?,?,?,?) RETURNING user_id";
        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole());
            
            var rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getInt("user_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));
                return user;
            }
        }
        return null;
    }
}
