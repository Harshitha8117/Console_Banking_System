package server;

import model.User;
import util.InputUtil;

import java.util.UUID;

public class AuthService {

    private final FileService fileService = new FileService();

    // Register user, returns User if success, else null
    public User register() {
        System.out.print("Choose a username: ");
        String username = InputUtil.readLine().trim();

        // Check if user already exists
        if (fileService.loadUser(username) != null) {
            System.out.println("❌ Username already exists. Try a different one.");
            return null;
        }

        System.out.print("Choose a password: ");
        String password = InputUtil.readLine().trim();

        // Generate unique account number (UUID substring)
        String accountNumber = generateAccountNumber();

        User newUser = new User(username, password, accountNumber);
        boolean saved = fileService.saveUser(newUser);

        if (saved) {
            // Also create an empty account with 0 balance on registration
            fileService.saveAccount(new model.Account(accountNumber, 0.0));
            System.out.println("✅ Registration successful. Your account number: " + accountNumber);
            return newUser;
        } else {
            System.out.println("❌ Error saving user.");
            return null;
        }
    }

    // Login user, returns User if credentials match else null
    public User login() {
        System.out.print("Username: ");
        String username = InputUtil.readLine().trim();

        System.out.print("Password: ");
        String password = InputUtil.readLine().trim();

        User user = fileService.loadUser(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("✅ Login successful. Welcome, " + username + "!");
            return user;
        } else {
            System.out.println("❌ Invalid credentials.");
            return null;
        }
    }

    private String generateAccountNumber() {
        // Simple unique ID, 8 chars long
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
