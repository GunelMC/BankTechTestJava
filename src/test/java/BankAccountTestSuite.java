import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class BankAccountTestSuite {
    private class MockTransaction implements Transaction {

        @Override
        public Integer getAmount() {
            return 500;
        }

        @Override
        public LocalDate getDate() {
            return LocalDate.of(2021, 1, 14);
        }

        @Override
        public Integer getBalance() {
            return 500;
        }
    }

    protected class MockTransactionCreater implements TransactionCreater {
        private int amount;
        private LocalDate date;
        private int balance;

        @Override
        public MockTransaction create(int amount, LocalDate date, int currentBalance) {
            this.amount = amount;
            this.date = date;
            this.balance = currentBalance;

            return new MockTransaction();
        }

        public int getAmount() {
            return amount;
        }

        public LocalDate getDate() {
            return date;
        }

        public int getBalance() {
            return balance;
        }
    }

    private MockTransactionCreater mockTransactionCreater;
    private BankStatementPrinter mockStatementPrinter;
    private BankAccount bankAccount;

    @BeforeEach
    private void setUp(){
        mockTransactionCreater = new MockTransactionCreater();
        mockStatementPrinter = Mockito.mock(BankStatementPrinter.class);
        bankAccount = new BankAccount(mockTransactionCreater, mockStatementPrinter);
    }

    @Test
    public void withdrawCreatesANewTransaction() {
        bankAccount.withdraw(500, LocalDate.of(2021, 1, 14));
        assertThat(mockTransactionCreater.getAmount(), equalTo(500) );
        assertThat(mockTransactionCreater.getDate(), equalTo(LocalDate.of(2021, 1, 14)) );
        assertThat(mockTransactionCreater.getBalance(), equalTo(-500) );
    }

    @Test
    public void depositCreatesANewTransaction() {
        bankAccount.deposit(500, LocalDate.of(2021, 2, 15));
        assertThat(mockTransactionCreater.getAmount(), equalTo(500) );
        assertThat(mockTransactionCreater.getDate(), equalTo(LocalDate.of(2021, 2, 15)) );
        assertThat(mockTransactionCreater.getBalance(), equalTo(500) );
    }

    @Test
    public void depositCreatesANewTransactionWithTheRightBalance() {
        for(int i=0; i<2; i++) {
            bankAccount.deposit(500, LocalDate.of(2021, 2, 15));
        }
        assertThat(mockTransactionCreater.getBalance(), equalTo(1000) );
    }

    @Test
    public void withdrawCreatesANewTransactionWithTheRightBalance() {
        bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        assertThat(mockTransactionCreater.getBalance(), equalTo(0) );
    }

    @Test
    public void generateStatementCallsStatementPrinter() {
        BankTransactionCreater mockTransactionCreater = Mockito.mock(BankTransactionCreater.class);
        BankStatementPrinter mockStatementPrinter = Mockito.mock(BankStatementPrinter.class);
        BankAccount bankAccount = new BankAccount(mockTransactionCreater, mockStatementPrinter);

        BankTransaction mockTransaction = Mockito.mock(BankTransaction.class);
        Mockito.when(mockTransactionCreater.create(anyInt(), any(LocalDate.class), anyInt())).thenReturn(mockTransaction);

        bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        bankAccount.generateStatement();

        Mockito.verify(mockStatementPrinter).printStatement(Arrays.asList(mockTransaction, mockTransaction, mockTransaction));
    }
}


