import java.time.LocalDate;

public interface TransactionCreater {
    public Transaction create(int amount, LocalDate date, int currentBalance);
}
