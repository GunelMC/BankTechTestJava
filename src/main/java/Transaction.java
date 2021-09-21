import java.time.LocalDate;

public interface Transaction {
    Integer getAmount();

    LocalDate getDate();

    Integer getBalance();
}
