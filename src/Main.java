import model.User;
import model.Account;
import server.AuthService;
import server.TransactionService;
import util.InputUtil;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static AuthService authService = new AuthService();
    static TransactionService transactionService = new TransactionService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n====== SecureBank CLI ======");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> authService.register();
                case "2" -> {
                    User user = authService.login();
                    if (user != null)
                        transactionService.dashboard(user);
                }
                case "3" -> {
                    System.out.println("Thanks for using SecureBank.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
