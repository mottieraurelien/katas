package event;

import domain.Player;

import java.math.BigDecimal;

public class LandOnLuxuryTaxEvent implements GameEvent {

    private static final BigDecimal LUXURY_TAX_REGULAR = new BigDecimal("75");
    private static final String LOG_MESSAGE = "Le joueur %s paie sa taxe de luxe.";

    private final Player player;

    LandOnLuxuryTaxEvent(final Player player) {
        this.player = player;
    }

    @Override
    public String process() {
        this.player.pay(LUXURY_TAX_REGULAR);
        return String.format(LOG_MESSAGE, this.player.getName());
    }

}
