import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    private static final Scanner sc = new Scanner(System.in);

    static class Color {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m"; // Error
        public static final String GREEN = "\u001B[32m"; // Successful
        public static final String YELLOW = "\u001B[33m"; // Warning
        public static final String BLUE = "\u001B[34m"; // Display Menu
        public static final String UNDERLINE = "\u001B[4m";
    }

    static public boolean validAmount(String amount) {
        return Pattern.matches("^\\d+(\\.\\d+)?$", amount);
    }

    private boolean userNameValidation(String userName) {
        return Pattern.matches("^[a-zA-Z\\s]+$", userName);
    }

    private boolean dateOfBirthValidation(String dateOfBirth) {
        Pattern pattern = Pattern.compile("(\\d{2})-(\\d{2})-(\\d{4})");
        Matcher matcher = pattern.matcher(dateOfBirth);
        if (matcher.matches()) {

            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));

            if (day < 1 || day > 31) {
                System.out.println(Color.RED + "[!] Invalid Day! Day cannot be under 0 or over 31!" + Color.RESET);
                return false;
            }

            if (month < 1 || month > 12) {
                System.out.println(Color.RED + "[!] Invalid Month! Month cannot be under 0 or over 12!" + Color.RESET);
                return false;
            }

            if (year < 1 || year > LocalDate.now().getYear()) {
                System.out.println(Color.RED + "[!] Invalid Year! Year cannot be 0 or over current year!" + Color.RESET);
                return false;
            }
            if (month == 2) {
                if (!((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) {
                    if (day >= 29) {
                        System.out.println(Color.RED + "[!] Invalid Day of the month " + year + " is not a leap year!" + Color.RESET);
                        return false;
                    }
                }
            }
            if (LocalDate.now().getYear() - year < 16) {
                System.out.println(Color.RED + "[!] Age must be over 16 years old to create bank account!" + Color.RESET);
                return false;
            }
        } else {
            System.out.println(Color.RED + "[!] Invalid Format DD-MM-YYYY!" + Color.RESET);
            return false;
        }
        return true;
    }

    private boolean phoneValidation(String phone) {
        return Pattern.matches("^0[1-9]\\d{7,8}$", phone);
    }

    public Account creatingAccount(String account) {

        printTitle("Input Account Information");

        String userName;
        //Input Username
        while (true) {
            System.out.print("[+] Please enter your username: ");
            userName = sc.nextLine().trim();
            if (userName.isEmpty()) {
                System.out.println(Color.RED + "[!] Username cannot be empty!" + Color.RESET);
            } else if (userNameValidation(userName)) {
                break;
            } else System.out.println(Color.RED + "[!] Username is invalid! Contains Letters Only!" + Color.RESET);
        }

        String dateOfBirth;
        //Input Username
        while (true) {
            System.out.print("[+] Please enter your dateOfBirth (DD-MM-YYYY): ");
            dateOfBirth = sc.nextLine().trim();
            if (dateOfBirth.isEmpty()) {
                System.out.println(Color.RED + "[!] Date of Birth cannot be empty!" + Color.RESET);
            } else if (dateOfBirthValidation(dateOfBirth)) {
                break;
            }
        }

        String gender;
        //Input Gender
        Selection:
        while (true) {
            System.out.println("[+] Please choose your gender (Male/Female)");
            System.out.print("""
                    1. Male
                    2. Female
                    """);
            System.out.print("=> Enter your choice: ");
            gender = sc.nextLine().trim();
            switch (gender) {
                case "1" -> {
                    gender = "Male";
                    break Selection;
                }
                case "2" -> {
                    gender = "Female";
                    break Selection;
                }
                default -> System.out.println(Color.RED + "[!] Invalid Input! Please Select Again 1-2!" + Color.RESET);
            }
        }

        String phone;
        //Input Phone
        while (true) {
            System.out.print("[+] Please enter your phone number: ");
            phone = sc.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println(Color.RED + "[!] Phone number cannot be empty!" + Color.RESET);
            } else if (phoneValidation(phone)) {
                break;
            } else System.out.println(Color.RED + "[!] Phone number is invalid!" + Color.RESET);
        }
        String accountID = String.valueOf((int) (Math.random() * 900000000) + 100000000).replaceAll("(.{3})(.{3})(.{3})", "$1 $2 $3");

        Account newAccount = null;
        if (account.equals("Checking Account")) {
            newAccount = new CheckingAccount(accountID, userName, dateOfBirth, gender, phone);
        }
        if (account.equals("Saving Account")) {
            newAccount = new SavingAccount(accountID, userName, dateOfBirth, gender, phone);
        }
        return newAccount;

    }

    public static void printTitle(String title) {
        int boxWidth = 60;
        int padding = (boxWidth - title.length()) / 2;
        String centeredText = String.format("|%" + (padding + title.length()) + "s%" + (boxWidth - padding - title.length() - 2) + "s|", title, "");

        for (int i = 0; i < boxWidth; i++) {
            System.out.print(Color.BLUE + "-" + Color.RESET);
        }
        System.out.println();

        System.out.println(Color.BLUE + centeredText + Color.RESET);

        for (int i = 0; i < boxWidth; i++) {
            System.out.print(Color.BLUE + "-" + Color.RESET);
        }
        System.out.println();
    }


    public String selectAccount(String operation) {
        String choice;
        Utilities.printTitle(operation + " Account");
        if (!operation.equals("Transfer")) {
            System.out.println("""
                    1. Checking Account
                    2. Saving Account
                    3. Back
                    """);
        } else {
            System.out.println("""
                    1. Checking Account -> Saving Account
                    2. Saving Account -> Checking Account
                    3. Back
                    """);
        }
        while (true) {
            System.out.print("=> Option : ");
            choice = sc.nextLine().trim();
            if (choice.isEmpty()) {
                System.out.println(Color.YELLOW + "[!] Choice Cannot be Empty!" + Color.RESET);
                continue;
            }
            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                return choice;
            } else System.out.println(Color.RED + "[!] Invalid Choice! Please choose from 1-3!" + Color.RESET);
        }
    }

    public void depositAndWithdraw(Account account, String operation) {
        String amount;
        double amountDouble;
        String remark;
        boolean success = false;
        Utilities.printTitle(operation + (account instanceof CheckingAccount ? " Checking Account" : " Saving Account"));

        if (operation.equals("Withdraw")) {
            if (account.getTotalBalance() == 0) {
                System.out.println("=".repeat(60));
                System.out.println(Color.YELLOW + "[!] Insufficient Amount! Balance = $" + account.getTotalBalance() + Color.RESET);
                System.out.println("=".repeat(60));
                return;
            }
        }

        while (true) {
            System.out.print("=> Input " + operation + " Amount : $");
            amount = sc.nextLine().trim();
            if (amount.isEmpty()) {
                System.out.println(Color.YELLOW + "[!] Amount cannot be empty!" + Color.RESET);
                continue;
            }
            if (!validAmount(amount)) {
                System.out.println(Color.RED + "[!] " + operation + " Amount is invalid!" + Color.RESET);
                continue;
            }
            amountDouble = Double.parseDouble(amount);
            if (amountDouble <= 10) {
                System.out.println(Color.RED + "[!] Deposit Amount cannot be less $10!" + Color.RESET);
                continue;
            }
            System.out.print("=> Enter Remark (Enter to Skip): ");
            remark = sc.nextLine().trim();

            if (operation.equals("Deposit")) {
                success = account.deposit(amountDouble);
            } else if (operation.equals("Withdraw")) {
                if (amountDouble > account.getTotalBalance()) {
                    System.out.println("=".repeat(60));
                    System.out.println(Color.YELLOW + "[!] Insufficient funds to withdraw! Total Balance = $" + account.getTotalBalance() + Color.RESET);
                    System.out.println("=".repeat(60));
                    break;
                }
                success = account.withdraw(amountDouble);
            }
            break;
        }
        if (success) {
            System.out.println(operation + " Amount : $" + amount);
            System.out.println("Total Balance  : " + account.getTotalBalance());
            System.out.println("=".repeat(60));
            System.out.println(Color.GREEN + "[*] " + (operation.equals("Deposit") ? "Deposited" : "Withdrawn") + " Successfully!" + Color.RESET);
            System.out.println("=".repeat(60));
            account.setTransactions(new Transaction(operation, getCurrentDateAndTime(), null, account.getAccountNameAndID(), amountDouble, remark));
        }
    }

    public void deleteAndTransfer(Account fromAccount, Account toAccount, String operation) {
        String title = operation.equals("Delete") ? operation + " Account" : (fromAccount instanceof CheckingAccount ? "Transfer From Checking Account -> Saving Account" : "Transfer From Saving Account -> Checking Account");
        Utilities.printTitle(title);
        switch (operation) {
            case "Transfer" -> transferMoney(fromAccount, toAccount);
            case "Delete" -> deleteAccount(fromAccount, toAccount);
        }
    }

    private void deleteAccount(Account account, Account transferAccount) {
        String remark;
        if (!isConfirm("Delete")) {
            return;
        }
        System.out.println("Transferring Remaining Balance to " + transferAccount.getAccountNameAndID());
        System.out.print("=> Enter Remark (Enter to Skip): ");
        remark = sc.nextLine();
        transferAccount.setTransactions(new Transaction("Received", getCurrentDateAndTime(), account.getAccountNameAndID(), transferAccount.getAccountNameAndID(), account.getTotalBalance(), remark));
        account.transfer(account.getTotalBalance(), transferAccount);

    }


    private void transferMoney(Account fromAccount, Account toAccount) {
        String amount;
        double amountDouble;
        String remark;
        if (fromAccount.getTotalBalance() == 0) {
            System.out.println("=".repeat(60));
            System.out.println(Color.YELLOW + "[!] Insufficient amount to transfer!" + Color.RESET);
            System.out.println("=".repeat(60));
            return;
        }
        while (true) {
            System.out.print("=> Input Transfer Amount : $");
            amount = sc.nextLine().trim();
            if (amount.isEmpty()) {
                System.out.println(Color.YELLOW + "Amount cannot be empty!" + Color.RESET);
            }
            if (validAmount(amount)) {
                amountDouble = Double.parseDouble(amount);
                if (amountDouble <= 0) {
                    System.out.println(Color.RED + "[!] Transfer Amount cannot be less than 0!" + Color.RESET);
                    continue;
                }
                if (amountDouble > fromAccount.getTotalBalance() * 0.8) {
                    System.out.println("=".repeat(60));
                    System.out.println(Color.YELLOW + "[!] Cannot Transfer $" + amount + ", because it is more than 80% of Total Balance!" + Color.RESET);
                    System.out.println("=".repeat(60));
                    continue;
                }
                System.out.print("=> Enter Remark (Enter to Skip): ");
                remark = sc.nextLine();

                if (!isConfirm("Transfer")) {
                    return;
                }
                if (fromAccount.transfer(amountDouble, toAccount)) {
                    Utilities.displayTransferInformation(fromAccount, toAccount, amountDouble);
                    System.out.println("=".repeat(50));
                    System.out.println(Color.GREEN + "[*] Transferred Successfully!" + Color.RESET);
                    System.out.println("=".repeat(50));

                    fromAccount.setTransactions(new Transaction("Transfer", getCurrentDateAndTime(), fromAccount.getAccountNameAndID(), toAccount.getAccountNameAndID(), amountDouble, remark));
                    toAccount.setTransactions(new Transaction("Received", getCurrentDateAndTime(), fromAccount.getAccountNameAndID(), toAccount.getAccountNameAndID(), amountDouble, remark));
                    return;
                }
            } else System.out.println(Color.RED + "[!] Inputted Amount is invalid!" + Color.RESET);
        }
    }


    public static void displayTransferInformation(Account fromAccount, Account toAccount, double amount) {

        String fromAcc = (fromAccount instanceof CheckingAccount) ? "Checking Account" : "Saving Account";
        String toAcc = (toAccount instanceof CheckingAccount) ? "Checking Account" : "Saving Account";

        Table table = new Table(2, BorderStyle.CLASSIC_LIGHT_WIDE, ShownBorders.ALL);
        table.addCell(fromAcc, new CellStyle(CellStyle.HorizontalAlign.center, CellStyle.AbbreviationStyle.dots), 2);
        table.addCell("Transferred       :", new CellStyle(CellStyle.HorizontalAlign.left, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("$ " + amount, new CellStyle(CellStyle.HorizontalAlign.right, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("From              :", new CellStyle(CellStyle.HorizontalAlign.left, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell(fromAcc + " with ID : " + fromAccount.getAccountNumber(), new CellStyle(CellStyle.HorizontalAlign.right, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("To                :", new CellStyle(CellStyle.HorizontalAlign.left, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell(toAcc + " with ID : " + toAccount.getAccountNumber(), new CellStyle(CellStyle.HorizontalAlign.right, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("Remaining Balance :", new CellStyle(CellStyle.HorizontalAlign.left, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("$ " + fromAccount.getTotalBalance(), new CellStyle(CellStyle.HorizontalAlign.right, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("⇓", new CellStyle(CellStyle.HorizontalAlign.center, CellStyle.AbbreviationStyle.dots), 2);
        table.addCell(toAcc, new CellStyle(CellStyle.HorizontalAlign.center, CellStyle.AbbreviationStyle.dots), 2);
        table.addCell("Received Amount   :", new CellStyle(CellStyle.HorizontalAlign.left, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("$ " + amount, new CellStyle(CellStyle.HorizontalAlign.right, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("Total Balance     :", new CellStyle(CellStyle.HorizontalAlign.left, CellStyle.AbbreviationStyle.dots), 1);
        table.addCell("$ " + toAccount.getTotalBalance(), new CellStyle(CellStyle.HorizontalAlign.right, CellStyle.AbbreviationStyle.dots), 1);

        table.setColumnWidth(0, 30, 50);
        table.setColumnWidth(1, 30, 50);

        System.out.println(table.render());
    }

    public void getTransactions(Account account1, Account account2) {
        if (account1.getTransactions()[0] != null) {

            if (account1 instanceof CheckingAccount) {
                printTitle("Checking Account Transaction History");
            } else printTitle("Saving Account Transaction History");

            for (Transaction trx : account1.getTransactions()) {
                if (trx != null) {
                    if (trx.getTrxType().equals("Transfer") || trx.getTrxType().equals("Received")) {
                        System.out.println("=".repeat(60));
                        System.out.println(trx.listTransaction(account1, account2));
                        System.out.println("=".repeat(60));
                    } else {
                        System.out.println("=".repeat(60));
                        System.out.println(trx.listTransaction(account1));
                        System.out.println("=".repeat(60));
                    }
                } else break;
            }
        } else {
            System.out.println();
            System.out.println(Color.YELLOW + "Transaction History is Empty!" + Color.RESET);
        }
    }

    public boolean isConfirm(String operation) {

        while (true) {
            System.out.print("Are you sure you want to " + operation + " ? (Y/N) ");
            String answer = sc.nextLine();
            if (Pattern.matches("[yYnN]", answer)) {
                return answer.equalsIgnoreCase("Y");
            } else System.out.println(Color.YELLOW + "[!] Invalid Input! Yes(y/Y) No(n/N)!" + Color.RESET);
        }
    }

    private String getCurrentDateAndTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd, yyyy HH:mm:ss");
        return currentDateTime.format(formatter);
    }
}

