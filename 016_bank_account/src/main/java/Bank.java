import domain.BankAccount;
import domain.OperationHistory;
import service.DateService;
import service.PrintService;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;

public class Bank {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount(new OperationHistory(), new DateService(), new PrintService());

        bankAccount.deposit(TEN);
        bankAccount.deposit(TEN);

        bankAccount.withdraw(ONE);
        bankAccount.withdraw(TEN);

        bankAccount.printStatement();

    }

}
