import java.time.LocalDate;

public class Transaction {
    private Integer amount;
    private LocalDate date;
    private Integer balance;

    public Transaction(Integer amount, LocalDate date, Integer balance) {
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getBalance() {
        return balance;
    }
}
