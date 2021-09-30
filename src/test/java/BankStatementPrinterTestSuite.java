import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankStatementPrinterTestSuite {
    private String expectBankStatement =
            "date || credit || debit || balance\n" +
            "14/01/2021 || - || 500.00 || 2500.00\n" +
            "13/01/2021 || 2000.00 || - || 3000.00\n" +
            "10/01/2021 || 1000.00 || - || 1000.00";

    Transaction bankTransaction1 = new BankTransaction(1000,LocalDate.of(2021, 1, 10) ,1000);
    Transaction bankTransaction2 = new BankTransaction(2000,LocalDate.of(2021, 1, 13) ,3000);
    Transaction bankTransaction3 = new BankTransaction(-500,LocalDate.of(2021, 1, 14) ,2500);

    @Test
    public void BankStatementPrinterPrintsBalanceCorrectly() {
        List<Transaction> transactionList = Arrays.asList(bankTransaction1, bankTransaction2, bankTransaction3);
        String actualBankStatement = BankStatementPrinter.printStatement(transactionList);

        assertEquals(expectBankStatement, actualBankStatement);
    }
}
