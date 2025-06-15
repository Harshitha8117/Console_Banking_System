
import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private String username;
    private String password;
    private double balance;
    private List<String> history;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.history = new ArrayList<>();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addTransaction(String entry) {
        history.add(entry);
    }

    public List<String> getHistory() {
        return history;
    }

    public String getUsername() {
        return username;
    }
}
