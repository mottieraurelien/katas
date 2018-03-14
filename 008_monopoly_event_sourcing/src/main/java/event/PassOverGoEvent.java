package event;

import domain.Player;

import java.math.BigDecimal;

public class PassOverGoEvent implements GameEvent {

    private static final BigDecimal BONUS = new BigDecimal(200);
    private static final String LOG_MESSAGE = "Le joueur %s reçoit 200$ car il est toujours dans la partie.";

    private final Player player;

    PassOverGoEvent(final Player player) {
        this.player = player;
    }

    @Override
    public String process() {
        this.player.receive(BONUS);
        return String.format(LOG_MESSAGE, this.player.getName());
    }

}
