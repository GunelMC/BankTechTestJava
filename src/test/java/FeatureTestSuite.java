import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FeatureTestSuite {

    @Test
    public void bankAccountPrintsBankStatement() {
        TransactionCreater transactionCreater = new BankTransactionCreater();
        BankStatementPrinter bankStatementPrinter = new BankStatementPrinter();
        BankAccount bankAccount = new BankAccount(transactionCreater, bankStatementPrinter);

        // Given a client makes a deposit of 1000 on 2021-01-10
        bankAccount.deposit(1000, LocalDate.of(2021, 1, 10));

        // And a deposit of 2000 on 2021-01-13
        bankAccount.deposit(2000, LocalDate.of(2021, 1, 13));

        // And a withdrawal of 500 on 2021-01-14
        bankAccount.withdraw(500, LocalDate.of(2021, 1, 14));

        // When she prints her bank statement
        String expectedBankStatement =
                "date || credit || debit || balance\n" +
                "14/01/2021 || - || 500.00 || 2500.00\n" +
                "13/01/2021 || 2000.00 || - || 3000.00\n" +
                "10/01/2021 || 1000.00 || - || 1000.00";
        assertThat(bankAccount.generateStatement(), equalTo(expectedBankStatement));
    }
}
