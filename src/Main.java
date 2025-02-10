import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Utilities utilities = new Utilities();


    public static void main(String[] args) {
        Account checkingAccount = null;
        Account savingAccount = null;

        Application:
        while (true) {
            String option;
            Utilities.printTitle("Online Banking System");
            System.out.println("""
                    1. Create Account
                    2. Deposit Money
                    3. Withdraw Money
                    4. Transfer Money
                    5. View Transaction History
                    6. Display Account Information
                    7. Delete Account
                    8. Exit
                    """);
            System.out.print("=> Option: ");
            option = scanner.nextLine().trim();
            switch (option) {

                //Creating Account

                case "1" -> {
                    String choice;
                    boolean isExist = false;
                    choice = utilities.selectAccount("Creating Account");
                    if (choice.equals("3")) {
                        break;
                    }

                    String accountType = choice.equals("1") ? "Checking Account" : "Saving Account";

                    if (choice.equals("1")) {
                        if (checkingAccount != null) {
                            isExist = true;
                        } else checkingAccount = utilities.creatingAccount(accountType);
                    }
                    if (choice.equals("2")) {
                        if (savingAccount != null) {
                            isExist = true;
                        } else savingAccount = utilities.creatingAccount(accountType);
                    }

                    if (isExist) {
                        System.out.println();
                        System.out.println(Utilities.Color.YELLOW + accountType + " already exists!" + Utilities.Color.RESET);
                        System.out.println();
                        System.out.println("Press Enter to continue....");
                        scanner.nextLine();
                    } else {
                        System.out.println();
                        System.out.println(Utilities.Color.GREEN + accountType + " created" + Utilities.Color.RESET);
                        System.out.println();
                        System.out.println("Press Enter to continue....");
                        System.out.println();
                        scanner.nextLine();
                    }

                }


                //Deposit Money

                case "2" -> {
                    String choice = utilities.selectAccount("Deposit");
                    Account selectedAccount = choice.equals("1") ? checkingAccount : savingAccount;
                    String accountType = choice.equals("1") ? "Checking Account" : "Saving Account";

                    if (choice.equals("3")) {
                        break;
                    }

                    if (selectedAccount != null) {
                        utilities.depositAndWithdraw(selectedAccount, "Deposit");
                    } else {
                        System.out.println(Utilities.Color.RED + "You don't have a " + accountType + " yet!" + Utilities.Color.RESET);
                    }

                    System.out.println("Press Enter to continue....");
                    scanner.nextLine();
                }

                //Withdraw Money

                case "3" -> {
                    String choice = utilities.selectAccount("Withdraw");
                    Account selectedAccount = choice.equals("1") ? checkingAccount : savingAccount;
                    String accountType = choice.equals("1") ? "Checking Account" : "Saving Account";

                    if (choice.equals("3")) {
                        break;
                    }

                    if (selectedAccount != null) {
                        utilities.depositAndWithdraw(selectedAccount, "Withdraw");
                    } else {
                        System.out.println(Utilities.Color.RED + "You don't have a " + accountType + " yet!" + Utilities.Color.RESET);
                    }

                    System.out.println("Press Enter to continue....");
                    scanner.nextLine();
                }

                //Transfer

                case "4" -> {
                    String choice = utilities.selectAccount("Transfer");
                    Account selectedAccount = choice.equals("1") ? checkingAccount : savingAccount;
                    Account transferToAccount = (selectedAccount instanceof CheckingAccount ? savingAccount : checkingAccount);

                    if (choice.equals("3")) {
                        break;
                    }

                    if (checkingAccount != null && savingAccount != null) {
                        utilities.deleteAndTransfer(selectedAccount, transferToAccount, "Transfer");
                    } else {
                        System.out.println();
                        System.out.println(Utilities.Color.RED + "You don't have a " + (checkingAccount == null ? "Checking" : "Saving") + " Account yet, cannot do transfer!" + Utilities.Color.RESET);
                    }

                    System.out.println();
                    System.out.println("Press Enter to continue....");
                    scanner.nextLine();

                }

                //View Transaction History

                case "5" -> {
                    String choice = utilities.selectAccount("Transaction History");
                    if (choice.equals("1")) {
                        if (checkingAccount != null) {
                            utilities.getTransactions(checkingAccount, savingAccount);
                            System.out.println();
                            System.out.println("Press Enter to continue....");
                            scanner.nextLine();
                        } else {
                            System.out.println();
                            System.out.println(Utilities.Color.RED + "You don't have a Checking Account yet!" + Utilities.Color.RESET);
                            System.out.println();
                            System.out.println("Press Enter to continue....");
                            scanner.nextLine();
                        }

                    } else if (choice.equals("2")) {
                        if (savingAccount != null) {
                            utilities.getTransactions(savingAccount, checkingAccount);
                            System.out.println();
                            System.out.println("Press Enter to continue....");
                            scanner.nextLine();
                        } else {
                            System.out.println();
                            System.out.println(Utilities.Color.RED + "You don't have a Saving Account yet!" + Utilities.Color.RESET);
                            System.out.println();
                            System.out.println("Press Enter to continue....");
                            scanner.nextLine();
                        }
                    }
                }

                //Display Account Information

                case "6" -> {
                    Utilities.printTitle("Displaying Account's Information");

                    if (checkingAccount != null) {
                        Utilities.printTitle("Checking Account's Information");
                        System.out.println(checkingAccount.displayAccountInfo());
                        System.out.println("=".repeat(60));
                    }
                    if (savingAccount != null) {
                        Utilities.printTitle("Saving Account's Information");
                        System.out.println(savingAccount.displayAccountInfo());
                        System.out.println("=".repeat(60));
                    }
                    if (checkingAccount == null && savingAccount == null) {
                        System.out.println();
                        System.out.println(Utilities.Color.RED + "There isn't any existing Account to display!" + Utilities.Color.RESET);
                    }

                    System.out.println();
                    System.out.println("Press Enter to continue....");
                    scanner.nextLine();

                }

                //Delete Account

                case "7" -> {
                    String choice = utilities.selectAccount("Delete");
                    Account selectedAccount = choice.equals("1") ? checkingAccount : savingAccount;
                    Account transferToAccount = (selectedAccount instanceof CheckingAccount ? savingAccount : checkingAccount);
                    boolean isDelete = false;
                    if (choice.equals("3")) {
                        break;
                    }

                    if (selectedAccount != null) {
                        if (selectedAccount.getTotalBalance() <= 0) {
                            if (!utilities.isConfirm("Delete")) break;
                            isDelete = true;
                        } else if (transferToAccount != null) {
                            utilities.deleteAndTransfer(selectedAccount, transferToAccount, "Delete");
                            isDelete = true;
                        } else {
                            System.out.println();
                            System.out.println(Utilities.Color.RED + "[!] Cannot delete Account because there is no other account to transfer remaining balance to!" + Utilities.Color.RESET);
                        }
                    } else {
                        System.out.println();
                        System.out.println(Utilities.Color.RED + "[!] There is no account to delete!" + Utilities.Color.RESET);
                    }                    if (isDelete) {
                        if (selectedAccount instanceof CheckingAccount) {
                            checkingAccount = null;
                        } else savingAccount = null;
                        System.out.println();
                        System.out.println(Utilities.Color.GREEN + "[*] Account has been deleted Successfully!" + Utilities.Color.RESET);
                    }
                    System.out.println();
                    System.out.println("Press Enter to continue....");
                    scanner.nextLine();
                }


                case "8" -> {
                    break Application;
                }
                case "" ->
                        System.out.println(Utilities.Color.YELLOW + "[!] Choice cannot be empty!" + Utilities.Color.RESET);

                default -> {
                    System.out.println();
                    System.out.println(Utilities.Color.RED + "Invalid Option! Please Choose From 1-8!" + Utilities.Color.RESET);
                    System.out.println();
                    System.out.println("Press Enter to continue....");
                    scanner.nextLine();
                }
            }
        }
        scanner.close();
    }
}

