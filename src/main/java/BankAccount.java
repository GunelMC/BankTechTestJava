import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final TransactionCreater transactionCreater;
    private final BankStatementPrinter bankStatementPrinter;
    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(TransactionCreater transactionCreater, BankStatementPrinter bankStatementPrinter){
        this.transactionCreater = transactionCreater;
        this.bankStatementPrinter = bankStatementPrinter;
    }

    public void withdraw(Integer amount, LocalDate date) {
        Transaction transaction = transactionCreater.create(-amount, date, getPreviousTransactionBalance()-amount);
        transactions.add(transaction);
    }

    public void deposit(Integer amount, LocalDate date) {
        Transaction transaction = transactionCreater.create(amount, date, getPreviousTransactionBalance()+amount);
        transactions.add(transaction);
    }

    public String generateStatement() {
        return bankStatementPrinter.printStatement(transactions);
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
