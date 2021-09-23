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
        Transaction transaction = transactionCreater.create(amount, date, getPreviousTransactionBalance()-amount);
    }

    public void deposit(Integer amount, LocalDate date) {
        Transaction transaction = transactionCreater.create(amount, date, getPreviousTransactionBalance()+amount);
        transactions.add(transaction);
    }

    private Integer getPreviousTransactionBalance(){
        if (transactions.size() == 0) {
            return 0;
        } else {
            Transaction lastTransaction = transactions.get(transactions.size()-1);
            return lastTransaction.getBalance();
        }
    }
}
