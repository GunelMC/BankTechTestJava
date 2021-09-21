import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final TransactionCreater transactionCreater;

    public BankAccount(TransactionCreater transactionCreater){
        this.transactionCreater = transactionCreater;
    }
    private final List<Transaction> transactions = new ArrayList<>();

    public void withdraw(Integer amount, LocalDate date) {
        Transaction transaction = transactionCreater.create(amount, date, getCurrentBalance());
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

    private Integer getCurrentBalance(){
        return 0;
    }
}
