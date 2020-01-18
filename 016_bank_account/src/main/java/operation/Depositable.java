package operation;

import domain.Operation;

import java.math.BigDecimal;

@FunctionalInterface
public interface Depositable {

    Operation deposit(final BigDecimal amount);

}