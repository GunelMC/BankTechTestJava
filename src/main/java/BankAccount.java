import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final List<Transaction> transactions = new ArrayList<>();

    public void withdraw(Integer amount, LocalDate date) {
        Transaction transaction = new Transaction(amount, date, getCurrentBalance());
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

    private Integer getCurrentBalance(){
        return 0;
    }
}
