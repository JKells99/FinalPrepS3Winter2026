package com.finalreview.musicstore.user;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

// Service layer, This handles all the business logic for the program and is sepearte from the db logic
public class UserService {

    private static UserDAO userDAO = new UserDAO();

    public void saveNewUser(User user) {
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Username, password, and email cannot be empty");
        }
        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPassword);
        userDAO.saveNewUserToDb(user);
        System.out.println("User saved successfully");
    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    public User getUserByUserName(String userName) throws SQLException {
        System.out.println("Getting user by username");
        return userDAO.getByUserName(userName);
    }

    public User logInToSystem(String userName, String password) throws SQLException {
        User user = getUserByUserName(userName);
        if(verifyPassword(password, user.getPassword())){
            System.out.println("Login Successful");
            return user;

        }else{
            System.out.println("Login Failed");
            return null;
        }
    }



}
