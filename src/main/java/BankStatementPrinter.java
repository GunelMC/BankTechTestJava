import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;

public class BankStatementPrinter {

    private static final String HEADER = "date || credit || debit || balance\n";

    private static String format(Transaction transaction) {
        if (transaction.getAmount() > 0) {
            String transactionLine = formatDate(transaction.getDate()) + " || " + formatCurrency(transaction.getAmount()) + " || - || " + formatCurrency(transaction.getBalance()) + "\n";
            return transactionLine;
        } else {
            String transactionLine = formatDate(transaction.getDate()) + " || - || " + formatCurrency(abs(transaction.getAmount())) + " || " + formatCurrency(transaction.getBalance()) + "\n";
            return transactionLine;
        }


    }

    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = date.format(formatter);
        return formattedString;
    }

    private static String formatCurrency(int amount) {
        String formattedAmount = String.format("%.2f", (float)amount);
        return formattedAmount;
    }

    public String printStatement(List<Transaction> transactions) {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER);

        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());

        for(Transaction transaction : transactions) {
            sb.append(format(transaction));
        }

        return sb.toString().trim();
    }
}
