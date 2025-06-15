
import java.util.Scanner;

public class Main {
    private static BankService bank = new BankService();
    private static Scanner scanner = new Scanner(System.in);
    private static String currentUser = null;

    public static void main(String[] args) {
        bank.loadDataFromFile();

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    bank.saveDataToFile();
                    System.exit(0);
            }
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        bank.registerUser(username, password);
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (bank.authenticate(username, password)) {
            currentUser = username;
            userMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Transfer\n4. History\n5. Logout");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Amount to deposit: ");
                    double depAmt = scanner.nextDouble();
                    bank.deposit(currentUser, depAmt);
                    break;
                case 2:
                    System.out.print("Amount to withdraw: ");
                    double wdAmt = scanner.nextDouble();
                    bank.withdraw(currentUser, wdAmt);
                    break;
                case 3:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Recipient username: ");
                    String to = scanner.nextLine();
                    System.out.print("Amount to transfer: ");
                    double tfAmt = scanner.nextDouble();
                    bank.transfer(currentUser, to, tfAmt);
                    break;
                case 4:
                    bank.printHistory(currentUser);
                    break;
                case 5:
                    currentUser = null;
                    return;
            }
        }
    }
}
