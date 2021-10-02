# Bank Test Java

### Requirements

* Implement deposits and withdrawals
* Implement an account statement that prints a heading row, and the date, amount
  and balance of each transaction, most recent first.
* You don't need to implement a command-line or user interface, test-driving
  and calling it in your main method.
* Data can be kept in memory, doesn't need to be in a database.

### Acceptance Criteria

Below is an example of how your code should be called, and what it should
output.

```java
// For: src/main/java/tech/makers/bank/Main.java
// Note the package:
package tech.makers.bank;

import java.time.LocalDate;

public class Main {
    public static void main(String args[]) {
        BankAccount bankAccount = new BankAccount();

        // Given a client makes a deposit of 1000 on 2021-01-10
        bankAccount.deposit(1000, LocalDate.of(2021, 1, 10));

        // And a deposit of 2000 on 2021-01-13
        bankAccount.deposit(2000, LocalDate.of(2021, 1, 13));

        // And a withdrawal of 500 on 2021-01-14
        bankAccount.withdraw(500, LocalDate.of(2021, 1, 14));

        // When she prints her bank statement
        System.out.println(bankAccount.generateStatement());
    }
}
```

Then she would see:

```
date || credit || debit || balance
14/01/2021 || - || 500.00 || 2500.00
13/01/2021 || 2000.00 || - || 3000.00
10/01/2021 || 1000.00 || - || 1000.00

```

### Domain Model

### Tech Stack
- Java
- JUnit5
- Hamcrest
- Mockito
