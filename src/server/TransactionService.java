package server;

import model.User;
import model.Account;
import server.FileService;
import util.InputUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionService {
    private static final String LOG_FILE = "data/transactions.txt";
    private final FileService fileService = new FileService();

    public void dashboard(User user) {
        Account account = fileService.loadAccount(user.getAccountNumber());
        if (account == null) {
            account = new Account(user.getAccountNumber(), 0.0);
            fileService.saveAccount(account);
        }

        while (true) {
            System.out.println("\n--- SecureBank Dashboard ---");
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. View Transactions");
            System.out.println("5. View Balance");
            System.out.println("6. Logout");
            System.out.print("Choose option: ");

            switch (InputUtil.readLine()) {
                case "1" -> deposit(account, user);
                case "2" -> withdraw(account, user);
                case "3" -> transfer(account, user);
                case "4" -> showLogs(user.getUsername());
                case "5" -> System.out.println("💰 Current Balance: ₹" + account.getBalance());
                case "6" -> {
                    System.out.println("🚪 Logged out. Thank you!");
                    return;
                }
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private void deposit(Account account, User user) {
        System.out.print("Enter deposit amount: ");
        double amount = InputUtil.readDouble();
        account.deposit(amount);
        fileService.saveAccount(account);
        log(user.getUsername(), "Deposited ₹" + amount);
        System.out.println("✅ ₹" + amount + " deposited successfully.");
    }

    private void withdraw(Account account, User user) {
        System.out.print("Enter withdrawal amount: ");
        double amount = InputUtil.readDouble();
        if (account.withdraw(amount)) {
            fileService.saveAccount(account);
            log(user.getUsername(), "Withdrew ₹" + amount);
            System.out.println("✅ ₹" + amount + " withdrawn.");
        } else {
            System.out.println("❌ Insufficient balance.");
        }
    }

    private void transfer(Account sender, User user) {
        System.out.print("Enter recipient account number: ");
        String recipientAcc = InputUtil.readLine();
        System.out.print("Enter amount to transfer: ");
        double amount = InputUtil.readDouble();

        if (!sender.withdraw(amount)) {
            System.out.println("❌ Insufficient balance.");
            return;
        }

        Account recipient = fileService.loadAccount(recipientAcc);
        if (recipient == null) {
            sender.deposit(amount); // rollback
            System.out.println("❌ Invalid recipient account.");
            return;
        }

        recipient.deposit(amount);
        fileService.saveAccount(sender);
        fileService.saveAccount(recipient);
        log(user.getUsername(), "Transferred ₹" + amount + " to " + recipientAcc);
        System.out.println("✅ ₹" + amount + " transferred to " + recipientAcc);
    }

    private void showLogs(String username) {
        System.out.println("📜 Transaction History:");
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains(username + ":")) {
                    System.out.println(line);
                    found = true;
                }
            }
            if (!found) System.out.println("No transactions found.");
        } catch (IOException e) {
            System.out.println("❌ Error reading log file.");
        }
    }

    private void log(String username, String action) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[" + timestamp + "] " + username + ": " + action);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("❌ Failed to log transaction.");
        }
    }
}
