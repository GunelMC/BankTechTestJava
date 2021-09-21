import java.time.LocalDate;

public class BankTransaction implements Transaction {
    private Integer amount;
    private LocalDate date;

    private Integer balance;

    public BankTransaction(Integer amount, LocalDate date, Integer balance) {
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }
}
