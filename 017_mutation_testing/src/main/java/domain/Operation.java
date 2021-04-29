package domain;

import java.math.BigDecimal;
import java.util.Date;

public class Operation {

    private final Date date;
    private final BigDecimal amount;
    private final BigDecimal balance;
    private final OperationType operationType;

    public Operation(final OperationType operationType, final Date date, final BigDecimal amount,
                     final BigDecimal balance) {
        this.operationType = operationType;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
