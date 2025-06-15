package com.bankapp.service;

import com.bankapp.model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private static final String DATA_FILE = "bank_data.dat";
    private Map<String, User> users;

    public BankService() {
        loadData();
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public void registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
            saveData();
        }
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).checkPassword(password);
    }

    public void deposit(String username, double amount) {
        if (amount <= 0) return;
        User user = users.get(username);
        user.setBalance(user.getBalance() + amount);
        user.addTransaction(LocalDateTime.now() + " - Deposited: ₹" + amount);
        saveData();
    }

    public void withdraw(String username, double amount) {
        if (amount <= 0) return;
        User user = users.get(username);
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            user.addTransaction(LocalDateTime.now() + " - Withdrew: ₹" + amount);
            saveData();
        }
    }

    public void transfer(String fromUser, String toUser, double amount) {
        if (amount <= 0 || !users.containsKey(toUser)) return;

        User sender = users.get(fromUser);
        User receiver = users.get(toUser);

        if (sender.getBalance() >= amount) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            sender.addTransaction(LocalDateTime.now() + " - Transferred: ₹" + amount + " to " + toUser);
            receiver.addTransaction(LocalDateTime.now() + " - Received: ₹" + amount + " from " + fromUser);
            saveData();
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving data.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            users = new HashMap<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new HashMap<>();
        }
    }

    public List<String> getHistory(String username) {
        return users.get(username).getHistory();
    }

    public double getBalance(String username) {
        return users.get(username).getBalance();
    }
}
