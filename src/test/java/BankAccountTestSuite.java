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
            return -500;
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

    @Test
    public void withdrawCreatesANewTransaction() {
        MockTransactionCreater mockTransactionCreater = new MockTransactionCreater();
        BankAccount bankAccount = new BankAccount(mockTransactionCreater);
        bankAccount.withdraw(500, LocalDate.of(2021, 1, 14));
        assertThat(mockTransactionCreater.getAmount(), equalTo(500) );
        assertThat(mockTransactionCreater.getDate(), equalTo(LocalDate.of(2021, 1, 14)) );
        assertThat(mockTransactionCreater.getBalance(), equalTo(-500) );
    }
}
