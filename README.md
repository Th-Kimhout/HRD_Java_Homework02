# HRD_Java_Homework02 Online Bank Management System

## Overview

The **Online Bank Management System** is a Java-based application that allows users to manage bank accounts efficiently. It supports two types of accounts:

- **Checking Account**
- **Saving Account**

Users can perform essential banking operations such as:

- Deposit
- Withdraw
- Transfer funds between accounts
- Delete an account
- View transaction history

Additionally, the savings account offers an interest incentive where deposits over \$200 receive an extra 5% interest, applied directly to the entire amount over \$200.

## Features

- Create and manage **Checking** and **Saving** accounts.
- **Deposit** money into an account.
- **Withdraw** funds from an account.
- **Transfer** funds between accounts.
- **Delete** an account when needed.
- **View transaction history** to track all deposits, withdrawals, and transfers.
- **Interest Calculation**: If a deposit into a Savings Account exceeds \$200, an additional 5% interest is directly applied to the amount over \$200.

## Installation & Setup

### Prerequisites:

- Java Development Kit (JDK) 8 or later

### Steps to Run:

1. Clone the repository or download the project files.
   ```sh
   git clone https://github.com/Th-Kimhout/HRD_Java_Homework02.git
   ```
2. Navigate to the project directory.
   ```sh
   cd HRD_Java_Homework02
   ```
3. Compile the Java files.
   ```sh
   javac Main.java
   ```
4. Run the application.
   ```sh
   java Main
   ```

## Usage Guide

1. **Creating an Account**
   - Choose between Checking or Saving account.
2. **Depositing Funds**
   - If you deposit more than \$200 in a Savings Account, you earn an extra 5% directly applied to the amount over \$200.
3. **Withdrawing Funds**
   - Ensure your balance is sufficient to withdraw.
4. **Transferring Funds**
   - Move money between Checking and Saving accounts.
5. **Viewing Transaction History**
   - Check past transactions, including deposits, withdrawals, and transfers.
6. **Deleting an Account**
   - Remove an account if no longer needed.

## Interest Calculation Example

- If a user deposits **\$300** into a **Savings Account**:
  - The calculation will be \$300 earns **5% extra** (\$300 × 5% = \$15).
  - The final deposit balance will be **\$315**.

## Technologies Used

- Java (OOP principles)

## Future Enhancements

- Implement a **Graphical User Interface (GUI)**.
- Add **authentication & security** features.
- Introduce **automated monthly interest** for savings accounts.

## Author

Developed by **[Theam Kimhout]**

Feel free to contribute and improve this project!

