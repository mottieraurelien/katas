package domain;

import operation.Depositable;
import operation.StatementPrintable;
import operation.Withdrawable;
import service.DateService;
import service.PrintService;

import java.math.BigDecimal;

import static domain.OperationType.DEPOSIT;
import static domain.OperationType.WITHDRAWAL;
import static java.math.BigDecimal.ZERO;

public class BankAccount implements Depositable, Withdrawable, StatementPrintable {

    private BigDecimal balance = ZERO;
    private final OperationHistory operationHistory;
    private final DateService dateService;
    private final PrintService printService;

    public BankAccount(final OperationHistory operationHistory, final DateService dateService,
                       final PrintService printService) {
        this.operationHistory = operationHistory;
        this.dateService = dateService;
        this.printService = printService;
    }

    @Override
    public Operation deposit(final BigDecimal amount) {

        checkAmountIsPositive(amount);

        balance = balance.add(amount);

        Operation newOperation = new Operation(DEPOSIT, dateService.getCurrentDate(), amount, balance);

        operationHistory.addOperation(newOperation);

        return newOperation;
    }

    @Override
    public Operation withdraw(final BigDecimal amount) {

        checkAmountIsPositive(amount);

        balance = balance.subtract(amount);

        Operation newOperation = new Operation(WITHDRAWAL, dateService.getCurrentDate(), amount.negate(), balance);

        operationHistory.addOperation(newOperation);

        return newOperation;
    }

    @Override
    public void printStatement() {
        System.out.println(printService.getPrintableStatement(operationHistory));
    }

    private void checkAmountIsPositive(final BigDecimal amount) {
        if (ZERO.compareTo(amount) > 0) {
            throw new IllegalArgumentException("Negative amounts are not allowed.");
        }
    }

}