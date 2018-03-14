package event;

import domain.Player;

public class MoveToLocationEvent implements GameEvent {

    private static final String LOG_MESSAGE = "Le joueur %s est arrivé à la case %s.";

    private final Player player;
    private final Integer targetedLocation;

    MoveToLocationEvent(final Player player, final Integer targetedLocation) {
        this.player = player;
        this.targetedLocation = targetedLocation;
    }

    @Override
    public String process() {
        this.player.moveTo(this.targetedLocation);
        return String.format(LOG_MESSAGE, this.player.getName(), this.player.getLocation());
    }

}
