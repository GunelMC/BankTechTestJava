import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BankAccountTestSuite {
    @Test
    public void withdrawCreatesANewTransaction() {
        TransactionCreater bankTransactionCreater = new BankTransactionCreater();
        BankAccount bankAccount = new BankAccount(bankTransactionCreater);
        bankAccount.withdraw(500, LocalDate.of(2021, 1, 14));
        List<Transaction> transactions = bankAccount.getTransactions();
        Transaction lastTransaction = transactions.get(transactions.size()-1);
        assertThat(lastTransaction.getAmount(), equalTo(500));
        assertThat(lastTransaction.getDate(), equalTo(LocalDate.of(2021, 1, 14)));
    }
}
