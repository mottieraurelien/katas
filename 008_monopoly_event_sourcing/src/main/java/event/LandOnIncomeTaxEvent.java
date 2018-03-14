package event;

import domain.Player;

import java.math.BigDecimal;

public class LandOnIncomeTaxEvent implements GameEvent {

    private static final BigDecimal INCOME_TAX_REGULAR = new BigDecimal("0.2");
    private static final BigDecimal INCOME_TAX_MAX_AMOUNT = new BigDecimal("200");
    private static final String LOG_MESSAGE = "Le joueur %s paie sa taxe d'impôts sur le revenu.";

    private final Player player;

    LandOnIncomeTaxEvent(final Player player) {
        this.player = player;
    }

    @Override
    public String process() {
        BigDecimal computedIncomeTaxWithRegular = this.player.getBalance().multiply(INCOME_TAX_REGULAR);
        if (INCOME_TAX_MAX_AMOUNT.compareTo(computedIncomeTaxWithRegular) < 0) {
            this.player.pay(INCOME_TAX_MAX_AMOUNT);
        } else {
            this.player.pay(computedIncomeTaxWithRegular);
        }
        return String.format(LOG_MESSAGE, this.player.getName());
    }

}
