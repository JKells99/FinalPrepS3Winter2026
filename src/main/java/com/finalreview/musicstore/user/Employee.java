package com.finalreview.musicstore.user;

public class Employee  extends User{
    public Employee(String username, String password, String email) {
        super(username, password, email, "EMPLOYEE");
    }

    public Employee(int userId, String username, String password, String email) {
        super(userId, username, password, email, "EMPLOYEE");
    }

}
