package operation;

import domain.Operation;

import java.math.BigDecimal;

@FunctionalInterface
public interface Withdrawable {

    Operation withdraw(final BigDecimal amount);

}