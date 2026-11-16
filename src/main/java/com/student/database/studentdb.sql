
CREATE DATABASE IF NOT EXISTS student_db;

USE student_db;

CREATE TABLE IF NOT EXISTS students (
    roll_no VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,
    student_year INT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    gender VARCHAR(20) NOT NULL
);
