# 💻 Console Banking System (Java + HTML/CSS Frontend)

A simulated multi-user banking system built with Java (Servlets) and a basic HTML/CSS interface. Users can register, log in, deposit, withdraw, transfer funds, and view transaction history. Persistence is maintained using file-based storage (`bank_data.dat`).

---

## 🧠 Features

- ✅ User registration & authentication
- 💰 Deposit, Withdraw, Transfer
- 🧾 View transaction history
- 🔐 File-based persistence
- 🖥️ Simple, responsive HTML/CSS frontend
- 🧩 Scalable backend using Java Servlets

---

## 🗂️ Folder Structure

console-banking-system/
├── backend/
│ ├── src/
│ │ └── com/bankapp/
│ │ ├── model/ # User model
│ │ ├── service/ # Core banking logic
│ │ └── servlet/ # Java servlets (Login, Register, Dashboard)
│ └── webapp/
│ ├── WEB-INF/
│ │ └── web.xml # Servlet mappings
│ ├── index.html # Login page
│ ├── register.html # Registration page
│ └── dashboard.html # Main dashboard


## ⚙️ Technologies

- Java 8+
- Java Servlets (Jakarta/Javax)
- HTML/CSS (Frontend)
- Apache Tomcat 9.x

## 🚀 Setup Instructions

1. **Install Apache Tomcat**  
   [Download Tomcat 9](https://tomcat.apache.org/download-90.cgi) → Extract to a known location

2. **Compile the Project**
   ```bash
   cd backend/src
   javac -cp "path-to-tomcat/lib/servlet-api.jar" -d ../webapp/WEB-INF/classes com/bankapp/model/*.java com/bankapp/service/*.java com/bankapp/servlet/*.java

Deploy to Tomcat

Copy webapp folder contents into:
TOMCAT_DIR/webapps/BankApp/

Start Tomcat

bash
Copy
Edit
cd TOMCAT_DIR/bin
startup.bat
Access the App

bash
Copy
Edit
http://localhost:8080/BankApp/index.html
✨ Features
✅ User Registration & Login

💰 Deposit / Withdraw / Transfer

📜 Transaction History

👤 Session-based Dashboard

🔒 Notes
No database used — in-memory session-based storage.

Extendable with JDBC/MySQL or JPA for persistence.

📌 Author
Harshitha
Project built as part of a hands-on learning journey in Java Web Development.

