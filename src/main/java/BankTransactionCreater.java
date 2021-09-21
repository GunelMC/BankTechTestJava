import java.time.LocalDate;

public class BankTransactionCreater implements TransactionCreater {
    public BankTransactionCreater() {
    }
    @Override
    public BankTransaction create(int amount, LocalDate date, int currentBalance) {
        return new BankTransaction(amount, date, currentBalance);
    }
}
