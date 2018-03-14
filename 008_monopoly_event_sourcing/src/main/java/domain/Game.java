package domain;

import exception.IncorrectNumberOfPlayersException;

import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.shuffle;
import static java.util.Collections.unmodifiableList;

public class Game {

    private static final Integer NB_MIN_PLAYERS = 2;
    private static final Integer NB_MAX_PLAYERS = 8;

    private final List<Player> players;

    Game(final List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IncorrectNumberOfPlayersException("A monopoly game can't start without any player.");
        }
        Integer numberOfPlayers = players.size();
        if (numberOfPlayers < NB_MIN_PLAYERS || numberOfPlayers > NB_MAX_PLAYERS) {
            final String errorMessage = format("A monopoly game can't handle %s players.", numberOfPlayers);
            throw new IncorrectNumberOfPlayersException(errorMessage);
        }
        shuffle(players);
        this.players = players;
    }

    public void removePlayer(final Player player) {
        this.players.remove(player);
    }

    List<Player> getPlayers() {
        return unmodifiableList(players);
    }

    void playRound() {
        // TODO
    }
}