package com.finalreview.musicstore;

import com.finalreview.musicstore.product.Customer;
import com.finalreview.musicstore.product.Employee;
import com.finalreview.musicstore.product.User;
import com.finalreview.musicstore.product.UserService;

import java.sql.SQLException;

public class MusicStoreTest {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();


        // Customer user = new Customer("username3", "password4", "j@j3.com");
        Employee emp2 = new Employee("username4", "password5", "emp@emp.com");


        userService.saveNewUser(emp2);

        User user = userService.logInToSystem("username4", "password5");

        if(user.getRole().equals("EMPLOYEE")){
            System.out.println("Welcome Employee");
        } else if(user.getRole().equals("CUSTOMER")){
            System.out.println("Welcome Customer");
        } else {
            System.out.println("Invalid Role Please dont be a noob");
        }


    }
}


// Final break down
// basic RBAC (role based access control)
// Manging data persistence with JDBC ( DAOS)
// 3 layer architecture just cause its easier to navigate and seperates concerns
// Writing to a file with file writer ( basic logger)
// Keeping the repo clean and organized
// See that you can write some SQL ( this brings in your DB course)
// If working as a team or solo we like to see basic git workflow used
// Building features for the program ( i.e searching, getting value of something, CRUD, report generation
// Trello board for project management or GitHub board for project management