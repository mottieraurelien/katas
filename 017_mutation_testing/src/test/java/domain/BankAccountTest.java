package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.DateService;

import java.math.BigDecimal;
import java.util.Date;

import static java.time.Instant.now;
import static java.util.Date.from;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private static final Date DATE = from(now());

    private static final BigDecimal INVALID_WITHDRAWAL_AMOUNT = BigDecimal.TEN;

    private static final BigDecimal DEPOSIT = new BigDecimal(100);
    private static final BigDecimal WITHDRAWAL = new BigDecimal(100);

    private OperationHistory operationHistory;
    private BankAccount bankAccount;

    @BeforeEach
    public void before_each() {

        final DateService dateService = new DateService() {
            @Override
            public Date getCurrentDate() {
                return DATE;
            }
        };

        operationHistory = new OperationHistory();
        bankAccount = new BankAccount(operationHistory, dateService);

    }

    @Test
    public void given_a_positive_deposit_amount_then_returns_the_new_associated_operation() {

        final Operation actual = bankAccount.deposit(DEPOSIT);

        assertNotNull(actual);
        assertEquals(OperationType.DEPOSIT, actual.getOperationType());
        assertEquals(DATE, actual.getDate());
        assertEquals(DEPOSIT, actual.getAmount());
        assertEquals(DEPOSIT, actual.getBalance());

        final Operation expectedAddedOperation = operationHistory.getAllOperations().get(0);
        assertEquals(expectedAddedOperation, actual);

    }

    @Test
    public void given_an_invalid_withdrawal_amount_then_throws_an_IllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(INVALID_WITHDRAWAL_AMOUNT));

    }

    @Test
    public void given_an_invalid_deposit_amount_then_throws_an_IllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(INVALID_WITHDRAWAL_AMOUNT));

    }

    @Test
    public void given_a_positive_withdrawal_amount_returns_the_new_associated_operation() {

        final Operation actual = bankAccount.withdraw(WITHDRAWAL);

        assertNotNull(actual);
        assertEquals(OperationType.WITHDRAWAL, actual.getOperationType());
        assertEquals(DATE, actual.getDate());
        assertEquals(WITHDRAWAL.negate(), actual.getAmount());
        assertEquals(WITHDRAWAL.negate(), actual.getBalance());

        final Operation expectedAddedOperation = operationHistory.getAllOperations().get(0);
        assertEquals(expectedAddedOperation, actual);

    }

}