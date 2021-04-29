package domain;

import operation.Depositable;
import operation.Withdrawable;
import service.DateService;

import java.math.BigDecimal;

import static domain.OperationType.DEPOSIT;
import static domain.OperationType.WITHDRAWAL;
import static java.math.BigDecimal.ZERO;

public class BankAccount implements Depositable, Withdrawable {

    private static final BigDecimal MINIMUM_DEPOSIT = new BigDecimal(100);
    private static final BigDecimal MINIMUM_WITHDRAWAL = new BigDecimal(100);

    private BigDecimal balance = ZERO;

    private final OperationHistory operationHistory;
    private final DateService dateService;

    public BankAccount(final OperationHistory operationHistory, final DateService dateService) {
        this.operationHistory = operationHistory;
        this.dateService = dateService;
    }

    @Override
    public Operation deposit(final BigDecimal amount) {

        checkDeposit(amount);

        balance = balance.add(amount);

        final Operation newOperation = new Operation(DEPOSIT, dateService.getCurrentDate(), amount, balance);

        operationHistory.addOperation(newOperation);

        return newOperation;
    }

    @Override
    public Operation withdraw(final BigDecimal amount) {

        checkWithdrawal(amount);

        balance = balance.subtract(amount);

        final Operation newOperation = new Operation(WITHDRAWAL, dateService.getCurrentDate(), amount.negate(), balance);

        operationHistory.addOperation(newOperation);

        return newOperation;
    }

    private static void checkDeposit(final BigDecimal amount) {
        if (MINIMUM_DEPOSIT.compareTo(amount) > 0) {
            throw new IllegalArgumentException("Depositing less than 100 HKD is not allowed.");
        }
    }

    private static void checkWithdrawal(final BigDecimal amount) {
        if (MINIMUM_WITHDRAWAL.compareTo(amount) > 0) {
            throw new IllegalArgumentException("Withdrawing less than 100 HKD is not allowed.");
        }
    }

}