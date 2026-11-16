# Student Record Management System

A desktop-based **Student Record Management System** developed using Java Swing and MySQL. This application helps administrators efficiently manage student information through a simple graphical user interface.

The system supports adding, viewing, updating, deleting, and searching student records, with MySQL providing persistent data storage.

## 📌 Project Overview

The Student Record Management System is designed to simplify student data management in schools and colleges. It provides an easy-to-use interface where administrators can maintain student details and perform essential record management operations.

## ✨ Features

- **Admin Login:** Login screen for administrator access.
- **Add Student:** Add new student records with details such as roll number, name, course, year, email, phone number, and gender.
- **View Students:** Display all student records in a JTable.
- **Update Student:** Search for an existing student and update their information.
- **Delete Student:** Remove student records with a confirmation prompt.
- **Search Students:** Search records by roll number, student name, or course.
- **Input Validation:** Validate required fields, email format, and phone number.
- **Duplicate Prevention:** Prevent duplicate roll numbers and email addresses.
- **Database Integration:** Store student information persistently using MySQL and JDBC.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 22 | Core programming language |
| Java Swing | Graphical User Interface |
| MySQL | Database management |
| JDBC | Java-MySQL connectivity |
| Maven | Project and dependency management |
| VS Code | Development environment |
| MySQL Workbench | Database administration |

## 📂 Project Structure

```text
Student-Record-Management-System/
│
├── database/
│   └── student_db.sql
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── student/
│                   │
│                   ├── Main.java
│                   │
│                   ├── database/
│                   │   └── DBConnection.java
│                   │
│                   ├── model/
│                   │   └── Student.java
│                   │
│                   ├── dao/
│                   │   └── StudentDAO.java
│                   │
│                   └── gui/
│                       ├── LoginFrame.java
│                       ├── DashboardFrame.java
│                       ├── AddStudentFrame.java
│                       ├── ViewStudentsFrame.java
│                       ├── UpdateStudentFrame.java
│                       ├── DeleteStudentFrame.java
│                       └── SearchStudentFrame.java
│
├── pom.xml
├── .gitignore
└── README.md
```

## 🗄️ Database Configuration

This project uses MySQL for storing student records.

**Database Name:** `student_db`

**Table Name:** `students`

### Student Table Structure

| Column Name | Data Type | Description |
|---|---|---|
| roll_no | VARCHAR(20) | Student roll number (Primary Key) |
| name | VARCHAR(100) | Student name |
| course | VARCHAR(100) | Course enrolled |
| student_year | INT | Academic year |
| email | VARCHAR(100) | Student email (Unique) |
| phone | VARCHAR(20) | Contact number |
| gender | VARCHAR(20) | Student gender |

### Database Setup

1. Install MySQL Server and MySQL Workbench.
2. Open MySQL Workbench.
3. Open the SQL script located at `database/student_db.sql`.
4. Execute the script to create the database and students table.
5. Configure your MySQL connection details in `DBConnection.java`.

**Important:** Do not upload your actual database password to GitHub. Use environment variables or another secure configuration method for database credentials.

## ⚙️ Installation and Setup

### Prerequisites

Make sure the following software is installed:

- JDK 22 or compatible Java version
- Apache Maven
- MySQL Server
- MySQL Workbench
- Git

### Step 1: Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/Student-Record-Management-System.git
```

### Step 2: Open the Project

```bash
cd Student-Record-Management-System
```

Open the project folder in VS Code.

### Step 3: Configure MySQL

- Start your MySQL Server.
- Execute the SQL script from the `database` folder.
- Configure the database URL, username, and password in `DBConnection.java` or your environment variables.

### Step 4: Build the Project

Open the terminal in the project directory and execute:

```bash
mvn clean compile
```

### Step 5: Run the Application

Execute:

```bash
mvn exec:java -Dexec.mainClass="com.student.Main"
```

The Student Record Management System login window should now appear.

## 🔐 Admin Login

The application currently uses demo administrator credentials.

| Field | Value |
|---|---|
| Username | `admin` |
| Password | `admin123` |

**Note:** These are demonstration credentials for the current project implementation. They should be replaced with secure authentication before production use.

## 🖥️ Application Modules

1. **Login Module:** Provides the administrator login interface.
2. **Dashboard Module:** Provides navigation to all student management operations.
3. **Add Student Module:** Adds new student information to the database.
4. **View Student Module:** Displays stored records in a table.
5. **Update Student Module:** Modifies existing student information.
6. **Delete Student Module:** Deletes selected student records.
7. **Search Student Module:** Finds student records using different search criteria.

## 🎯 Learning Outcomes

Through this project, the following concepts are demonstrated:

- Object-Oriented Programming in Java.
- GUI development using Java Swing.
- Event handling and exception handling.
- Database connectivity using JDBC.
- CRUD operations using MySQL.
- SQL queries and prepared statements.
- Maven-based Java project management.
- Basic input validation and data integrity.

## 🚀 Future Enhancements

- Secure administrator authentication.
- Password hashing and role-based access.
- Export student records to PDF or Excel.
- Improved dashboard design.
- Student profile management.
- Advanced search and filtering.
- Backup and restore functionality.

