package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.DateService;
import service.PrintService;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private static final Date DATE = Date.from(Instant.now());
    private static final BigDecimal AMOUNT = BigDecimal.ONE;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String PRINTABLE_STATEMENT = "PRINTABLE_STATEMENT";

    private BankAccount bankAccount;
    private OutputStream outputStream;

    @BeforeEach
    public void beforeEach() {

        final OperationHistory operationHistory = new OperationHistory();

        final DateService dateService = new DateService() {
            @Override
            public Date getCurrentDate() {
                return DATE;
            }
        };

        final PrintService printService = new PrintService() {
            @Override
            public String getPrintableStatement(OperationHistory operationHistory) {
                return PRINTABLE_STATEMENT;
            }
        };

        outputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        bankAccount = new BankAccount(operationHistory, dateService, printService);

    }

    @Test
    public void given_a_positive_deposit_amount_then_returns_the_new_associated_operation() {

        final Operation actual = bankAccount.deposit(AMOUNT);

        assertNotNull(actual);
        assertEquals(OperationType.DEPOSIT, actual.getOperationType());
        assertEquals(DATE, actual.getDate());
        assertEquals(AMOUNT, actual.getAmount());
        assertEquals(AMOUNT, actual.getBalance());

    }

    @Test
    public void given_a_negative_deposit_amount_then_throws_an_IllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(AMOUNT.negate()));

    }

    @Test
    public void given_a_positive_withdrawal_amount_returns_the_new_associated_operation() {

        final Operation actual = bankAccount.withdraw(AMOUNT);

        assertNotNull(actual);
        assertEquals(OperationType.WITHDRAWAL, actual.getOperationType());
        assertEquals(DATE, actual.getDate());
        assertEquals(AMOUNT.negate(), actual.getAmount());
        assertEquals(AMOUNT.negate(), actual.getBalance());

    }

    @Test
    public void given_a_negative_withdrawal_amount_then_throws_an_IllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(AMOUNT.negate()));

    }

    @Test
    public void given_few_operations_then_returns_printable_statement() {

        bankAccount.deposit(AMOUNT);
        bankAccount.withdraw(AMOUNT);

        bankAccount.printStatement();

        assertEquals(PRINTABLE_STATEMENT + LINE_SEPARATOR, outputStream.toString());

    }

}
