-- ATM Machine Database Schema
-- Create database
CREATE DATABASE IF NOT EXISTS atm_system;

-- Use the database
USE atm_system;

-- Table for storing user account details
CREATE TABLE IF NOT EXISTS users (
    account_number VARCHAR(20) PRIMARY KEY,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(15) NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    pin_attempts INT DEFAULT 0,
    is_locked INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table for storing transactions
CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    balance_after DECIMAL(15, 2) NOT NULL,
    description VARCHAR(255),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_number) REFERENCES users(account_number)
);

-- Table for storing OTP attempts (for security)
CREATE TABLE IF NOT EXISTS otp_logs (
    otp_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    otp_code VARCHAR(6) NOT NULL,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    is_verified INT DEFAULT 0,
    FOREIGN KEY (account_number) REFERENCES users(account_number)
);

-- Insert sample users (account numbers of 7-8 digits, passwords hashed with SHA-256)
-- Note: In production, use proper password hashing. These are example hashes.
INSERT INTO users (account_number, password_hash, full_name, email, mobile_number, balance) VALUES
('12345678', '5e884898da28047151d0e56f8dc62927538270d734bb9c4b8d962fa5f582aacd', 'John Doe', 'john@example.com', '9876543210', 50000.00),
('87654321', '5e884898da28047151d0e56f8dc62927538270d734bb9c4b8d962fa5f582aacd', 'Jane Smith', 'jane@example.com', '9876543211', 75000.00),
('11223344', '5e884898da28047151d0e56f8dc62927538270d734bb9c4b8d962fa5f582aacd', 'Bob Johnson', 'bob@example.com', '9876543212', 100000.00);

-- Create indices for faster queries
CREATE INDEX idx_account_number ON users(account_number);
CREATE INDEX idx_transaction_account ON transactions(account_number);
CREATE INDEX idx_otp_account ON otp_logs(account_number);

-- Display tables
SHOW TABLES;
