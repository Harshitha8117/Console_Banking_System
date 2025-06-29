---

SecureBank CLI

A lightweight, console-based banking application in Java offering secure user registration, login, account management, and transaction capabilities with file-based persistence.

---

## Features

* **User Authentication:** Register and login with username and password.
* **Account Management:** Automatically creates and maintains accounts with balance.
* **Transactions:** Deposit, withdraw, transfer funds securely.
* **Transaction History:** Logs and displays user-specific transaction records.
* **File Persistence:** All user, account, and transaction data stored in local text files.
* **Simple CLI:** Intuitive menu-driven console interface.

---

## Project Structure

```
securebank/
│
├── src/
│   ├── Main.java
│   ├── model/
│   ├── server/
│   └── util/
├── data/
├── README.md
└── .gitignore
```

---

## Getting Started

### Prerequisites

* Java JDK 11 or higher
* Command line terminal (PowerShell, Bash, etc.)

### Compile

```bash
javac model/*.java util/*.java server/*.java Main.java
```

### Run

```bash
java Main
```

---

## Usage

1. Run the app.
2. Choose options to Register, Login, or Exit.
3. Once logged in, manage your account via Deposit, Withdraw, Transfer, and view Transaction History.
4. Logout to exit to the main menu.

---

## Data Storage

* User credentials and account numbers saved in `data/users.txt`.
* Account balances saved in `data/accounts.txt`.
* Transaction logs saved in `data/transactions.txt`.

Files are automatically created and updated on operations.

---

## Extending & Customization Ideas

* Migrate file storage to a database for scalability.
* Add password hashing and enhanced security.
* Implement multi-currency support.
* Integrate with a web or GUI frontend.
* Add scheduled payments and notifications.

---

## License

MIT License © Harshitha

---

