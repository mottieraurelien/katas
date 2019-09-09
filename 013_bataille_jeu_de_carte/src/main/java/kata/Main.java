package kata;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import kata.domain.Deck;
import kata.domain.Player;
import kata.domain.WarCardGame;

import static java.util.stream.Stream.of;

public class Main {

    private static final int MAX_ROUNDS = 1000000;

    public static void main(String[] args) {

        // Creates all players :
        List<Player> players = new ArrayList<>();
        of(args).forEach(playerName -> players.add(new Player(playerName, new ArrayDeque<>())));

        // Generates and shuffles all cards that we are going to distribute to the players, and launches the game :
        WarCardGame warCardGame = new WarCardGame(new Deck(), players, MAX_ROUNDS);
        warCardGame.start();

    }

}