import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
    private BankAccount bankAccount;

    @BeforeEach
    private void setUp(){
        mockTransactionCreater = new MockTransactionCreater();
        bankAccount = new BankAccount(mockTransactionCreater);
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
}


