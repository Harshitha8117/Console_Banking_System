package server;

import model.User;
import model.Account;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class FileService {

    private static final Path USER_FILE = Paths.get("data/users.txt");
    private static final Path ACCOUNT_FILE = Paths.get("data/accounts.txt");

    public boolean saveUser(User user) {
        try {
            Files.createDirectories(USER_FILE.getParent());
            String line = user.getUsername() + ":" + user.getPassword() + ":" + user.getAccountNumber() + "\n";
            Files.write(USER_FILE, line.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save user: " + e.getMessage());
            return false;
        }
    }

    public User loadUser(String username) {
        if (!Files.exists(USER_FILE)) return null;
        try {
            for (String line : Files.readAllLines(USER_FILE)) {
                String[] parts = line.split(":");
                if (parts.length == 3 && parts[0].equals(username)) {
                    return new User(parts[0], parts[1], parts[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        }
        return null;
    }

    public void saveAccount(Account account) {
        try {
            Files.createDirectories(ACCOUNT_FILE.getParent());
            List<String> lines = new ArrayList<>();
            if (Files.exists(ACCOUNT_FILE)) {
                lines = Files.readAllLines(ACCOUNT_FILE);
                boolean updated = false;
                for (int i = 0; i < lines.size(); i++) {
                    String[] parts = lines.get(i).split(":");
                    if (parts.length >= 2 && parts[0].equals(account.getAccountNumber())) {
                        lines.set(i, account.getAccountNumber() + ":" + account.getBalance());
                        updated = true;
                        break;
                    }
                }
                if (!updated) {
                    lines.add(account.getAccountNumber() + ":" + account.getBalance());
                }
            } else {
                lines.add(account.getAccountNumber() + ":" + account.getBalance());
            }
            Files.write(ACCOUNT_FILE, lines);
        } catch (IOException e) {
            System.err.println("Failed to save account: " + e.getMessage());
        }
    }

    public Account loadAccount(String accNum) {
        if (!Files.exists(ACCOUNT_FILE)) return null;
        try {
            for (String line : Files.readAllLines(ACCOUNT_FILE)) {
                String[] parts = line.split(":");
                if (parts.length >= 2 && parts[0].equals(accNum)) {
                    double balance = Double.parseDouble(parts[1]);
                    return new Account(accNum, balance);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading account file: " + e.getMessage());
        }
        return null;
    }
}
