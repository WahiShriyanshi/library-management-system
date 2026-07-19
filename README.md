📚 Library Management System

A Core Java project for managing library operations including books, members, librarians, and transactions with fine management.

🛠️ Tech Stack


Java (Core)
Object-Oriented Programming
Collections Framework (HashMap, ArrayList)
Java Streams
Java Time API (LocalDate)
Custom Exception Handling
Enum Types


📁 Project Structure

LibraryManagementSystem/
│
├── Entity/
│   ├── User.java                  # Parent class for Member and Librarian
│   ├── Member.java                # Extends User, tracks pending fine
│   ├── Librarian.java             # Extends User
│   ├── Book.java                  # Book entity
│   ├── Transaction.java           # Transaction entity
│   ├── Shift.java                 # Enum: shifts for librarians
│   └── TransactionStatus.java     # Enum: ACTIVE, OVERDUE, RETURN
│
├── Exception/
│   ├── TransactionNotFound.java
│   ├── UserDoesNotExistsException.java
│   └── NewTransactionCannotBeCreatedException.java
│
└── Managment/
    ├── BookManagement.java         # CRUD for books
    ├── UserManagement.java         # CRUD for members and librarians
    └── TransactionManagement.java  # Issue/return books, fine calculation

✨ Features

User Management


Add/remove members and librarians
View all members, librarians, or users
Fetch member or librarian by ID
Check member's pending fine


Transaction Management


Issue book to member with automatic due date (14 days)
Block new transaction if member's pending fine ≥ ₹1000
Auto-detect overdue transactions on access
Calculate fine on return (₹50/day after due date)
Update member's pending fine on return
Track total fine and paid fine per transaction
Check if transaction fine is fully paid


Fine Tracking


Fine calculated automatically on book return
Partial payment support via paidFine field in Transaction
isFullyPaid() check available in Transaction class
Member's cumulative pendingFine updated on each return


⚙️ How It Works

Issue Book
    ↓
Check member's pendingFine < ₹1000
    ↓
Create Transaction → status: ACTIVE, dueDate: issueDate + 14 days
    
Return Book
    ↓
Check if overdue → update status to OVERDUE
    ↓
Calculate fine (days late × ₹50)
    ↓
Update member pendingFine + transaction totalFine
    ↓
Set status: RETURN, returnDate: today

🔮 Upcoming
MySQL + JDBC integration
Fine payment system
Library orchestrator class connecting all services


👩‍💻 Author
Shriyanshi Wahi
Update member pendingFine + transaction totalFine
    ↓
Set status: RETURN, returnDate: today
