import exception.IncorrectNumberOfPlayersException;
import exception.IncorrectNumberOfRoundsException;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Game {

    private static final Integer NB_MIN_PLAYERS = 2;
    private static final Integer NB_MAX_PLAYERS = 8;
    private final List<Player> players;

    Game(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IncorrectNumberOfPlayersException("A monopoly game can't start without any player.");
        }
        Integer numberOfPlayers = players.size();
        if (numberOfPlayers < NB_MIN_PLAYERS || numberOfPlayers > NB_MAX_PLAYERS) {
            throw new IncorrectNumberOfPlayersException(
                    String.format("A monopoly game can't handle %s players.", numberOfPlayers));
        }
        Collections.shuffle(players);
        this.players = Collections.unmodifiableList(players);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void start(Integer numberOfRoundsToPlay) {

        if (numberOfRoundsToPlay == null || numberOfRoundsToPlay < 1) {
            throw new IncorrectNumberOfRoundsException(
                    String.format("Players can't play %s rounds", numberOfRoundsToPlay));
        }

        IntStream.iterate(1, n -> n++).limit(numberOfRoundsToPlay).forEach(currentRound -> {
            this.players.forEach(Player::play);
        });

    }

}
