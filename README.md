# ConsoleBankingSystem

A secure, file-based console banking application in Java supporting user registration, login, and essential banking operations with persistent storage.

---

## Project Structure

ConsoleBankingSystem/
│
├── src/
│ ├── model/
│ │ ├── User.java
│ │ └── Account.java
│ │
│ ├── service/
│ │ ├── AuthService.java
│ │ ├── TransactionService.java
│ │ └── FileService.java
│ │
│ ├── util/
│ │ └── InputUtil.java
│ │
│ └── Main.java
│
├── data/
│ ├── users.txt
│ └── transactions.txt
│
├── web/
│ ├── index.html
│ └── style.css
│
├── README.md
└── compile_run.bat

Data Storage
data/users.txt — stores user credentials and account numbers.

data/transactions.txt — logs all transactions with timestamps.

Future Improvements
Password hashing and encryption

Database integration

Web or GUI frontend

Support concurrent multi-user sessions with thread safety

Author
Harshitha — Passionate about building innovative, scalable solutions.
