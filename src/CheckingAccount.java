import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckingAccount extends Account {


    public CheckingAccount(String accountNumber, String username, String dateOfBirth, String gender, String phoneNumber) {
        super(accountNumber, username, dateOfBirth, gender, phoneNumber);
    }

    @Override
    boolean deposit(double amount) {

        setTotalBalance(getTotalBalance() + amount);
        return true;

    }

    @Override
    boolean withdraw(double amount) {
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

        return "Account Type    : Checking Account" + "\n" +
                "Account Number  : " + getAccountNumber() + "\n" +
                "User Name       : " + getUsername() + "\n" +
                "Date of Birth   : " + getDateOfBirth() + "\n" +
                "Gender          : " + getGender() + "\n" +
                "Phone Number    : " + getPhoneNumber() + "\n" +
                "Balance         : $" + getTotalBalance() + "\n";

    }


}
