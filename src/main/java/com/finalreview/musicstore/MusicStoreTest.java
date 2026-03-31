package com.finalreview.musicstore;

import com.finalreview.musicstore.product.Accessory;
import com.finalreview.musicstore.product.Instrument;
import com.finalreview.musicstore.product.ProductDAO;
import com.finalreview.musicstore.product.ProductService;
import com.finalreview.musicstore.user.Customer;
import com.finalreview.musicstore.user.Employee;
import com.finalreview.musicstore.user.User;
import com.finalreview.musicstore.user.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class MusicStoreTest {
    public static final UserService userService = new UserService();
    public static final ProductService productService = new ProductService();
    public static void main(String[] args) throws SQLException {


//        // Customer user = new Customer("username3", "password4", "j@j3.com");
//        Employee emp2 = new Employee("username4", "password5", "emp@emp.com");
//
//        userService.saveNewUser(emp2);
//
//        Instrument instrument = new Instrument("Fender Strat","6 String Fender Electric",1199.00,30,"Guitar","Fender",6,"Black");
//        Accessory accessory = new Accessory("Guitar Strings","D'Addario Nickel Wound",9.99,100,"Strings","D'Addario");
//
//        System.out.println("\n--- Testing Product Persistence ---");
//
//
//        System.out.println("\n--- Testing Generic Retrieval ---");
//        // Test fetching all instruments
//        productService.getAllInstruments();
//
//        // Test fetching all accessories
//        productService.getAllAccessories();

        // RBAC
        User user = null;
        Scanner scanner = new Scanner(System.in);

        displayWelcomeMenu(scanner);
    }

    private static void displayWelcomeMenu(Scanner scanner) {
        boolean running = true;
        while(running){
            System.out.println("\n--- Welcome to the Music Store ---");
            System.out.println("Please select an option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            try{
                switch (choice){
                    case 1:
                       loginMenu(scanner);
                        break;
                    case 2:
                        registerMenu(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        running = false;
                }
            } catch (Exception e){
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine();
            }
        }
    }

    private static void registerMenu(Scanner scanner) {
        // Username, email, passwiord and role

        System.out.println("--- Register New User ---");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter role (customer/employee): ");
        String role = scanner.next();

        if(role.equalsIgnoreCase("customer") || role.equalsIgnoreCase("employee")){
            User user = new User(username, password, email, role);
            userService.saveNewUser(user);
            System.out.println("User registered successfully!");
        }

    }

    private static void loginMenu(Scanner scanner) throws SQLException {

        System.out.println("--- User Login ---");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        User user = userService.logInToSystem(username, password);
        loginRoleCheck(user,scanner);
    }

    private static void loginRoleCheck(User user, Scanner scanner) {
        if(user !=null && user.getRole().equalsIgnoreCase("CUSTOMER")){
            customerMenu(scanner);
        } else if(user != null && user.getRole().equalsIgnoreCase("EMPLOYEE")){
            employeeMenu(scanner);
        }
    }

    private static void employeeMenu(Scanner scanner) {
        // Search for prouct in stock ( name, type, price, colour)
        // REmove customer from system
        // Get total value of inventory ( sum of all products including number in stock)
        boolean inEmployeeMenu = true;
        while(inEmployeeMenu){
            System.out.println("--- Employee Menu ---");
            System.out.println("1. Add New Product");
            System.out.println("2. View Products");
            System.out.println("3. Add New Customer");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    createProductMenu(scanner);
                    break;
                case 2:
                    viewProductMenu(scanner);
                    break;

                case 3:
                    addingCustomerMenu(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Employee Menu...");
                    inEmployeeMenu = false;
            }
        }

        System.out.println("--- Employee Menu ---");
    }

    private static void addingCustomerMenu(Scanner scanner) {
        System.out.println("--- Adding New Customer ---");
        System.out.print("Enter customer's name: ");
        String customerName = scanner.next();
        System.out.println("Enter customer's email: ");
        String customerEmail = scanner.next();
        System.out.println("Enter customer's phone number: ");
        String customerPhone = scanner.next();
        Customer customer = new Customer(customerName, customerEmail, customerPhone);
        userService.saveNewUser(customer);
        System.out.println("Customer added successfully!");
    }

    private static void viewProductMenu(Scanner scanner) {
        System.out.println("--- View Products ---");
        System.out.println("1. View All Instruments");
        System.out.println("2. View All Accessories");
        System.out.println("3. Back to Employee Menu");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                productService.getAllInstruments();
                break;
            case 2:
                productService.getAllAccessories();
                break;
        }
    }

    private static void createProductMenu(Scanner scanner) {
        System.out.println("--- Add New Product Menu ---");
        System.out.println("1. Add New Instrument");
        System.out.println("2. Add New Accessory");
        System.out.println("3. Back to Employee Menu");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter instrument name: ");
                String instrumentName = scanner.next();
                System.out.println("Enter instrument description: ");
                String instrumentDescription = scanner.next();
                System.out.println("Enter instrument price: ");
                double instrumentPrice = scanner.nextDouble();
                System.out.println("Enter instrument stock: ");
                int instrumentStock = scanner.nextInt();
                System.out.println("Enter instrument type: ");
                String instrumentType = scanner.next();
                System.out.println("Enter instrument brand: ");
                String instrumentBrand = scanner.next();
                System.out.println("Enter number of strings (if applicable, else enter 0): ");
                int numberOfStrings = scanner.nextInt();
                System.out.println("Enter instrument color: ");
                String instrumentColor = scanner.next();
                Instrument newInstrument = new Instrument(instrumentName, instrumentDescription, instrumentPrice, instrumentStock, instrumentType, instrumentBrand, numberOfStrings, instrumentColor);
                productService.saveInstrument(newInstrument);
                break;
            case 2:
                System.out.println("Enter accessory name: ");
                String accessoryName = scanner.nextLine();
                System.out.println("Enter accessory description: ");
                String accessoryDescription = scanner.next();
                System.out.println("Enter accessory price: ");
                double accessoryPrice = scanner.nextDouble();
                System.out.println("Enter accessory stock: ");
                int accessoryStock = scanner.nextInt();
                System.out.println("Enter accessory type: ");
                String accessoryType = scanner.next();
                System.out.println("Enter accessory brand: ");
                String accessoryBrand = scanner.next();
                Accessory newAccessory = new Accessory(accessoryName, accessoryDescription, accessoryPrice, accessoryStock, accessoryType, accessoryBrand);
                productService.saveAccessory(newAccessory);
                break;

        }
    }

    private static void customerMenu(Scanner scanner) {
        System.out.println("--- Customer Menu ---");
        // search products by name, type, price, colour
        // Purchase products
        // View All Products
        // View Products By Catergory
        // Purchase Product
        // View Order History

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