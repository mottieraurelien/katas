package event;

import domain.Game;
import domain.Player;

public class RunOutOfMoneyEvent implements GameEvent {

    private static final String LOG_MESSAGE = "Le joueur %s quitte la partie car il n'a plus d'argent.";

    private final Game game;
    private final Player player;

    RunOutOfMoneyEvent(final Game game, final Player player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public String process() {
        this.game.removePlayer(player);
        return String.format(LOG_MESSAGE, this.player.getName());
    }

}
