import java.util.Arrays;

public abstract class Account {
    private final String accountNumber; //Random ID
    private String username;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private double totalBalance = 0.0;
    private Transaction[] transactions = new Transaction[10];

    public String getAccountNameAndID() {
        return username + " (" + accountNumber + ")";
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction newTrx) {
        int trackTrxCount = 0;
        for (Transaction trx : transactions) {
            if (trx == null) {
                break;
            } else trackTrxCount++;
        }
        if (trackTrxCount == transactions.length) {
            transactions = Arrays.copyOf(transactions, transactions.length + 10);
        }
        this.transactions[trackTrxCount] = newTrx;
    }

    public Account(String accountNumber, String username, String dateOfBirth, String gender, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    abstract boolean deposit(double amount);

    abstract boolean withdraw(double amount);

    abstract boolean transfer(double amount, Account targetAccount);

    abstract String displayAccountInfo();

}
