public class Transaction {
    private final String trxID;
    private final String trxType;
    private final String trxDate;
    private final String fromAccountID;
    private final String toAccountID;
    private final double amount;
    private final String remark;

    public String getTrxType() {
        return trxType;
    }

    public Transaction(String trxType, String trxDate, String fromAccountID, String toAccountID, double amount, String remark) {
        this.trxID = String.valueOf((int) (Math.random() * 900000) + 100000).replaceAll("(.{3})(.{3})(.{3})", "$1 $2 $3");
        this.trxType = trxType;
        this.trxDate = trxDate;
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;
        this.amount = amount;
        this.remark = remark;
    }


    public String listTransaction(Account fromAccount, Account toAccount) {
        return "Transaction ID    : " + trxID + '\n' +
                "Transaction Type  : " + trxType + '\n' +
                "Transaction Date  : " + trxDate + '\n' +
                "From Account      : " + fromAccountID + '\n' +
                "To Account        : " + toAccountID + '\n' +
                "Amount            : $" + amount + '\n' +
                (remark.isEmpty() ? "" : "Remark            : " + remark);
    }

    public String listTransaction(Account toAccount) {
        return "Transaction ID    : " + trxID + '\n' +
                "Transaction Type  : " + trxType + '\n' +
                "Transaction Date  : " + trxDate + '\n' +
                "To Account        : " + toAccountID + '\n' +
                "Amount            : $" + amount + '\n' +
                (remark.isEmpty() ? "" : "Remark            : " + remark);
    }
}
