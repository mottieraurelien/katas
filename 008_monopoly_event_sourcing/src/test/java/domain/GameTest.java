package domain;

import exception.IncorrectNumberOfPlayersException;
import mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameTest {

    private static final String PLAYER_ONE_NAME = "Car";
    private static final String PLAYER_TWO_NAME = "Horse";

    @Test
    void given_a_player_that_runs_out_of_money_is_removed_from_the_game(
            @Mock Player playerOne, @Mock Player playerTwo) {

        Game game = new Game(new LinkedList<>(Arrays.asList(playerOne, playerTwo)));
        assertThat(game.getPlayers()).containsExactly(playerOne, playerTwo);

        game.removePlayer(playerTwo);

        assertThat(game.getPlayers()).containsExactly(playerOne);

    }

    @Test
    void starting_a_new_game_without_any_player_throws_an_IncorrectNumberOfPlayersException() {

        assertThrows(IncorrectNumberOfPlayersException.class, () -> new Game(null));

    }

    @Test
    void starting_a_new_game_with_only_one_player_throws_an_IncorrectNumberOfPlayersException(
            @Mock List<Player> playersMock) {

        when(playersMock.size()).thenReturn(1);

        assertThrows(IncorrectNumberOfPlayersException.class, () -> new Game(playersMock));

    }

    @Test
    void starting_a_new_game_with_too_many_players_throws_an_IncorrectNumberOfPlayersException(
            @Mock List<Player> playersMock) {

        when(playersMock.size()).thenReturn(9);

        assertThrows(IncorrectNumberOfPlayersException.class, () -> new Game(playersMock));

    }

    @Test
    void starting_a_new_game_with_two_players_initializes_the_game_and_shuffles_the_order_of_the_players() {

        Boolean playerOneThenPlayerTwoWillPlay = Boolean.FALSE;
        Boolean playerTwoThenPlayerOneWillPlay = Boolean.FALSE;
        Player playerOne = new Player(PLAYER_ONE_NAME, null, null);
        Player playerTwo = new Player(PLAYER_TWO_NAME, null, null);
        List<Player> players = new LinkedList<>(Arrays.asList(playerOne, playerTwo));

        for (int i = 1; i <= 100; i++) {
            Game game = new Game(players);
            if (!playerOneThenPlayerTwoWillPlay && game.getPlayers().get(0) == playerOne) {
                playerOneThenPlayerTwoWillPlay = Boolean.TRUE;
            } else if (!playerTwoThenPlayerOneWillPlay && game.getPlayers().get(0) == playerTwo) {
                playerTwoThenPlayerOneWillPlay = Boolean.TRUE;
            }
            if (playerOneThenPlayerTwoWillPlay && playerTwoThenPlayerOneWillPlay) {
                break;
            }
        }

        assertEquals(Boolean.TRUE, playerOneThenPlayerTwoWillPlay);
        assertEquals(Boolean.TRUE, playerTwoThenPlayerOneWillPlay);

    }

}
