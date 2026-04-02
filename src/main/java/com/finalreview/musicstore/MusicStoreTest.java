package com.finalreview.musicstore;

import com.finalreview.musicstore.invoice.Invoice;
import com.finalreview.musicstore.invoice.InvoiceService;
import com.finalreview.musicstore.product.Accessory;
import com.finalreview.musicstore.product.Instrument;
import com.finalreview.musicstore.product.Product;
import com.finalreview.musicstore.product.ProductService;
import com.finalreview.musicstore.user.Customer;
import com.finalreview.musicstore.user.User;
import com.finalreview.musicstore.user.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicStoreTest {
    public static final UserService userService = new UserService();
    public static final ProductService productService = new ProductService();
    public static final InvoiceService invoiceService = new InvoiceService();

    public static void main(String[] args) throws SQLException {
        System.out.println("--- Running Invoice Feature Test ---");
        testInvoiceFeature();
        System.out.println("--- Invoice Feature Test Complete ---\n");

        Scanner scanner = new Scanner(System.in);
        displayWelcomeMenu(scanner);
    }

    private static void displayWelcomeMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Welcome to the Music Store ---");
            System.out.println("Please select an option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            try {
                switch (choice) {
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
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine();
            }
        }
    }

    private static void registerMenu(Scanner scanner) {
        System.out.println("--- Register New User ---");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter role (customer/employee): ");
        String role = scanner.next();

        if (role.equalsIgnoreCase("customer") || role.equalsIgnoreCase("employee")) {
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
        loginRoleCheck(user, scanner);
    }

    private static void loginRoleCheck(User user, Scanner scanner) {
        if (user != null && user.getRole().equalsIgnoreCase("CUSTOMER")) {
            customerMenu(scanner);
        } else if (user != null && user.getRole().equalsIgnoreCase("EMPLOYEE")) {
            employeeMenu(scanner);
        }
    }

    private static void employeeMenu(Scanner scanner) {
        boolean inEmployeeMenu = true;
        while (inEmployeeMenu) {
            System.out.println("--- Employee Menu ---");
            System.out.println("1. Add New Product");
            System.out.println("2. View Products");
            System.out.println("3. Add New Customer");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
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
        switch (choice) {
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
        switch (choice) {
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
                System.out.println("Enter number of strings: ");
                int numberOfStrings = scanner.nextInt();
                System.out.println("Enter instrument color: ");
                String instrumentColor = scanner.next();
                Instrument newInstrument = new Instrument(instrumentName, instrumentDescription, instrumentPrice, instrumentStock, instrumentType, instrumentBrand, numberOfStrings, instrumentColor);
                productService.saveInstrument(newInstrument);
                break;
            case 2:
                System.out.println("Enter accessory name: ");
                String accessoryName = scanner.next();
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
    }

    private static void testInvoiceFeature() {
        User customer = new User("test_customer_" + System.currentTimeMillis(), "pass", "cust@test.com", "CUSTOMER");
        User employee = new User("test_employee_" + System.currentTimeMillis(), "pass", "emp@test.com", "EMPLOYEE");
        
        userService.saveNewUser(customer);
        userService.saveNewUser(employee);
        
        Instrument guitar = new Instrument("Gibson Les Paul", "Electric Guitar", 2499.00, 5, "Guitar", "Gibson", 6, "Heritage Cherry");
        Accessory picks = new Accessory("Fender Picks", "Medium Picks 12pk", 12.99, 100, "Picks", "Fender");
        
        productService.saveInstrument(guitar);
        productService.saveAccessory(picks);
        
        List<Product> purchasedItems = new ArrayList<>();
        purchasedItems.add(guitar);
        purchasedItems.add(picks);
        
        System.out.println("Processing purchase for customer ID: " + customer.getUserId());
        Invoice invoice = invoiceService.processPurchase(customer.getUserId(), employee.getUserId(), purchasedItems);
        
        invoiceService.printInvoice(invoice);
        if (invoice.getInvoice_id() > 0) {
            System.out.println("SUCCESS: Invoice saved with ID " + invoice.getInvoice_id());
        } else {
            System.out.println("FAILURE: Invoice was not saved correctly.");
        }
    }
}
