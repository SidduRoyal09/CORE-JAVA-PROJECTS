package com.project;

import java.util.*;

class User {
    String username;
    String password;
    double balance;
    List<String> transactionHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient balance.");
        }
        balance -= amount;
        transactionHistory.add("Withdrew: $" + amount);
    }

    public void transfer(User recipient, double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient balance.");
        }
        balance -= amount;
        recipient.balance += amount;
        transactionHistory.add("Transferred: $" + amount + " to " + recipient.username);
        recipient.transactionHistory.add("Received: $" + amount + " from " + username);
    }

    public void viewStatement() {
        System.out.println("Transaction History for " + username + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("Current Balance: $" + balance);
    }
}

class Admin {
    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";

    public boolean validateAdmin(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }
}

public class BankingSystem {
    private List<User> pendingAccounts = new ArrayList<>();
    private List<User> activeAccounts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void registerUser() {
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        User newUser = new User(username, password);
        pendingAccounts.add(newUser);
        System.out.println("Registration successful! Your account is pending admin approval.");
    }

    public void adminLogin() {
        Admin admin = new Admin();
        System.out.print("Enter admin username: ");
        String username = sc.next();
        System.out.print("Enter admin password: ");
        String password = sc.next();

        if (admin.validateAdmin(username, password)) {
            System.out.println("Admin login successful!");
            processPendingAccounts();
        } else {
            System.out.println("Invalid admin credentials!");
        }
    }

    private void processPendingAccounts() {
        Iterator<User> iterator = pendingAccounts.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            System.out.println("Approve account for username: " + user.username + "? (yes/no)");
            String response = sc.next();

            if (response.equalsIgnoreCase("yes")) {
                activeAccounts.add(user);
                iterator.remove();
                System.out.println("Account approved!");
            } else {
                System.out.println("Account rejected!");
            }
        }
    }

    public void userLogin() {
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        User currentUser = null;
        for (User user : activeAccounts) {
            if (user.username.equals(username) && user.password.equals(password)) {
                currentUser = user;
                break;
            }
        }

        if (currentUser != null) {
            System.out.println("Login successful!");
            performUserOperations(currentUser);
        } else {
            System.out.println("Invalid credentials or account not approved.");
        }
    }

    private void performUserOperations(User user) {
        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Transfer\n4. View Statement\n5. Logout");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter deposit amount: ");
                        double amount = sc.nextDouble();
                        user.deposit(amount);
                        System.out.println("Deposit successful!");
                    }
                    case 2 -> {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = sc.nextDouble();
                        user.withdraw(amount);
                        System.out.println("Withdrawal successful!");
                    }
                    case 3 -> {
                        System.out.print("Enter recipient username: ");
                        String recipientUsername = sc.next();
                        System.out.print("Enter transfer amount: ");
                        double amount = sc.nextDouble();

                        User recipient = activeAccounts.stream()
                                .filter(u -> u.username.equals(recipientUsername))
                                .findFirst()
                                .orElse(null);

                        if (recipient != null) {
                            user.transfer(recipient, amount);
                            System.out.println("Transfer successful!");
                        } else {
                            System.out.println("Recipient not found.");
                        }
                    }
                    case 4 -> user.viewStatement();
                    case 5 -> {
                        System.out.println("Logged out successfully.");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        BankingSystem system = new BankingSystem();

        while (true) {
            System.out.println("\n1. Register\n2. Admin Login\n3. User Login\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = system.sc.nextInt();

            switch (choice) {
                case 1 -> system.registerUser();
                case 2 -> system.adminLogin();
                case 3 -> system.userLogin();
                case 4 -> {
                    System.out.println("Exiting the system.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
