public class SavingAccount extends Account {

    private static final double RATE = 0.05;

    public SavingAccount(String accountNumber, String username, String dateOfBirth, String gender, String phoneNumber) {
        super(accountNumber, username, dateOfBirth, gender, phoneNumber);
    }

    @Override
    boolean deposit(double amount) {
        if (amount >= 200) {
            setTotalBalance(getTotalBalance() + amount + (amount * RATE));
        } else
            setTotalBalance(getTotalBalance() + amount);
        return true;
    }

    @Override
    boolean withdraw(double amount) {
        if (amount > (0.8 * getTotalBalance())) {
            System.out.println("=".repeat(60));
            System.out.println(Utilities.Color.YELLOW + "[!] Cannot Withdraw " + amount + ", because it is more than 80% of Total Balance!" + Utilities.Color.RESET);
            System.out.println("=".repeat(60));
            return false;
        }
        setTotalBalance(getTotalBalance() - amount);
        return true;
    }

    @Override
    boolean transfer(double amount, Account targetAccount) {
            setTotalBalance(getTotalBalance() - amount);
            targetAccount.setTotalBalance(targetAccount.getTotalBalance() + amount);
            return true;

    }

    @Override
    String displayAccountInfo() {
        return "Account Type    : Saving Account" + "\n" +
                "Account Number  : " + getAccountNumber() + "\n" +
                "User Name       : " + getUsername() + "\n" +
                "Date of Birth   : " + getDateOfBirth() + "\n" +
                "Gender          : " + getGender() + "\n" +
                "Phone Number    : " + getPhoneNumber() + "\n" +
                "Balance         : $" + getTotalBalance() + "\n";
    }
}
