import exception.IncorrectNumberOfPlayersException;
import exception.IncorrectNumberOfRoundsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    @Test
    @DisplayName("User Story 102 : Try to create a game without any players. When attempting to play the game, it will fail.")
    void starting_a_new_game_without_any_player_throws_an_IncorrectNumberOfPlayersException() {

        assertThrows(IncorrectNumberOfPlayersException.class, () -> new Game(null));

    }

    @Test
    @DisplayName("User Story 102 : Try to create a game with < 2 players. When attempting to play the game, it will fail.")
    void starting_a_new_game_with_only_one_player_throws_an_IncorrectNumberOfPlayersException() {

        List<Player> players = Collections.singletonList(new Player("Player one", BigDecimal.ZERO));

        assertThrows(IncorrectNumberOfPlayersException.class, () -> new Game(players));

    }

    @Test
    @DisplayName("User Story 102 : Try to create a game with > 8 players. When attempting to play the game, it will fail.")
    void starting_a_new_game_with_too_many_players_throws_an_IncorrectNumberOfPlayersException() {

        List<Player> players = Arrays.asList(new Player("Player one", BigDecimal.ZERO), new Player("Player two", BigDecimal.ZERO),
                new Player("Player three", BigDecimal.ZERO), new Player("Player four", BigDecimal.ZERO), new Player("Player five", BigDecimal.ZERO),
                new Player("Player six", BigDecimal.ZERO), new Player("Player seven", BigDecimal.ZERO), new Player("Player eight", BigDecimal.ZERO),
                new Player("Player nine", BigDecimal.ZERO));

        assertThrows(IncorrectNumberOfPlayersException.class, () -> new Game(players));

    }

    @RepeatedTest(100)
    @DisplayName("User Story 102 : Create a game with two players named Horse and Car. Within creating 100 games, both orders [Horse, Car] and [Car, Horse] occur.")
    void starting_a_new_game_with_two_players_initializes_the_game_and_shuffles_the_order_of_the_players() {

        Player carPlayer = new Player("Car", BigDecimal.ZERO);
        Player horsePlayer = new Player("Horse", BigDecimal.ZERO);
        List<Player> players = Arrays.asList(carPlayer, horsePlayer);

        Game game = new Game(players);

        assertThat(game.getPlayers(), containsInAnyOrder(carPlayer, horsePlayer));

    }

    @ParameterizedTest
    @CsvSource({"-1", "0"})
    @DisplayName("User Story 103 : Try to create a game with an incorrect number of rounds to play. When attempting to play the game, it will fail.")
    void starting_a_new_game_without_a_correct_number_of_rounds_throws_an_IncorrectNumberOfRoundsException(Integer inputNumberOfRoundsToPlay) {

        Player carPlayer = new Player("Car", BigDecimal.ZERO);
        Player horsePlayer = new Player("Horse", BigDecimal.ZERO);
        List<Player> players = Arrays.asList(carPlayer, horsePlayer);
        Game game = new Game(players);

        assertThrows(IncorrectNumberOfRoundsException.class, () -> game.start(inputNumberOfRoundsToPlay));

    }

    @ParameterizedTest
    @CsvSource({"1, 1", "20, 20"})
    @DisplayName("User Story 103 : Create a game and play, verify that in every round the order of the players remained the same.")
    void playing_several_rounds_does_not_alter_the_order_of_the_players(
            Integer inputNumberOfRoundsToPlay, Integer expectedPlayedRounds) {

        Player carPlayer = new Player("Car", BigDecimal.ZERO);
        Player horsePlayer = new Player("Horse", BigDecimal.ZERO);
        Game game = new Game(Arrays.asList(carPlayer, horsePlayer));
        Player expectedPlayerOne = game.getPlayers().get(0);
        Player expectedPlayerTwo = game.getPlayers().get(1);

        game.start(inputNumberOfRoundsToPlay);

        assertEquals(expectedPlayerOne, game.getPlayers().get(0));
        assertEquals(expectedPlayerTwo, game.getPlayers().get(1));

    }

    @Test
    @DisplayName("User Story 103 : Create a game and play, verify that it will fail when trying to modify the order of the players.")
    void trying_to_modify_the_order_of_players_when_playing_throws_an_UnsupportedOperationException() {

        Player carPlayer = new Player("Car", BigDecimal.ZERO);
        Player horsePlayer = new Player("Horse", BigDecimal.ZERO);

        Game game = new Game(Arrays.asList(carPlayer, horsePlayer)) {
            @Override
            public void start(Integer numberOfRoundsToPlay) {
                Collections.shuffle(this.getPlayers());
            }
        };

        assertThrows(UnsupportedOperationException.class, () -> game.start(1));

    }

    @ParameterizedTest
    @CsvSource({"1, 1", "20, 20"})
    @DisplayName("User Story 103 : Create a game and play, verify that the total rounds was 20 and that each player played 20 rounds.")
    void players_play_as_much_as_rounds_to_play(Integer inputNumberOfRoundsToPlay, Integer expectedPlayedRounds) {

        Player carPlayer = new Player("Car", BigDecimal.ZERO);
        Player horsePlayer = new Player("Horse", BigDecimal.ZERO);
        Game game = new Game(Arrays.asList(carPlayer, horsePlayer));

        game.start(inputNumberOfRoundsToPlay);

        assertEquals(expectedPlayedRounds, carPlayer.getPlayedRounds());
        assertEquals(expectedPlayedRounds, horsePlayer.getPlayedRounds());

    }

}
