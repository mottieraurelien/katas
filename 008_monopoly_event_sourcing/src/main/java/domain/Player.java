package domain;

import exception.IncorrectAmountException;
import exception.IncorrectLocationException;

import java.math.BigDecimal;

public class Player {

    private static final Integer FIRST_LOCATION = 0;
    private static final Integer LAST_LOCATION = 39;

    private final String name;
    private Integer location;
    private BigDecimal balance;

    Player(final String name, final Integer location, final BigDecimal balance) {
        this.name = name;
        this.location = location;
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public Integer getLocation() {
        return this.location;
    }

    public void receive(final BigDecimal amount) {
        this.rejectAmountIfNegative(amount);
        this.balance = this.balance.add(amount);
    }

    public void pay(final BigDecimal amount) {
        this.rejectAmountIfNegative(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void moveTo(final Integer location) {
        if (location < FIRST_LOCATION || location > LAST_LOCATION) {
            throw new IncorrectLocationException("La position spécifiée n'existe pas dans le jeu.");
        }
        this.location = location;
    }

    private void rejectAmountIfNegative(final BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IncorrectAmountException("Le montant spécifié ne peut être négatif.");
        }
    }

}
