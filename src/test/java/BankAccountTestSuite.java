import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;

public class BankAccountTestSuite {

    private BankTransactionCreater mockTransactionCreater;
    private BankStatementPrinter mockStatementPrinter;
    private BankAccount bankAccount;
    private BankTransaction mockTransaction;

    @BeforeEach
    private void setUp(){
        mockTransactionCreater = Mockito.mock(BankTransactionCreater.class);
        mockStatementPrinter = Mockito.mock(BankStatementPrinter.class);
        bankAccount = new BankAccount(mockTransactionCreater, mockStatementPrinter);
        mockTransaction = Mockito.mock(BankTransaction.class);
        Mockito.when(mockTransactionCreater.create(anyInt(), any(LocalDate.class), anyInt())).thenReturn(mockTransaction);
        Mockito.when(mockTransaction.getBalance()).thenReturn(500);
    }

    @Test
    public void withdrawCallsTransactionCreater() {
        bankAccount.withdraw(500, LocalDate.of(2021, 1, 14));
        Mockito.verify(mockTransactionCreater).create(-500,LocalDate.of(2021, 1, 14 ), -500);
    }

    @Test
    public void depositCallsTransactionCreater() {
        bankAccount.deposit(500, LocalDate.of(2021, 2, 15));
        Mockito.verify(mockTransactionCreater).create(500,LocalDate.of(2021, 2, 15 ), 500);
    }

    @Test
    public void depositCreatesANewTransactionWithTheRightBalance() {
        for(int i=0; i<2; i++) {
            bankAccount.deposit(500, LocalDate.of(2021, 2, 15));
        }
        Mockito.verify(mockTransactionCreater).create(anyInt(), any(LocalDate.class), eq(1000));
    }

    @Test
    public void withdrawCreatesANewTransactionWithTheRightBalance() {
        for(int i=0; i<2; i++) {
            bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        }
        Mockito.verify(mockTransactionCreater).create(anyInt(), any(LocalDate.class), eq(0));
    }

    @Test
    public void generateStatementCallsStatementPrinter() {
        bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        bankAccount.withdraw(500, LocalDate.of(2021, 2, 15));
        bankAccount.generateStatement();

        Mockito.verify(mockStatementPrinter).printStatement(Arrays.asList(mockTransaction, mockTransaction, mockTransaction));
    }
}


