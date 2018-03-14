package event;

import domain.Player;

public class LandOnGoToJailEvent implements GameEvent {

    private static final Integer JUST_VISITING_SQUARE_INDEX = 10;
    private static final String LOG_MESSAGE = "Le joueur %s est envoyé à la case \"Simple visite\".";

    private final Player player;

    LandOnGoToJailEvent(final Player player) {
        this.player = player;
    }

    @Override
    public String process() {
        this.player.moveTo(JUST_VISITING_SQUARE_INDEX);
        return String.format(LOG_MESSAGE, this.player.getName());
    }

}
