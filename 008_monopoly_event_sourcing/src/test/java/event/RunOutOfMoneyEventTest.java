package event;

import domain.Game;
import domain.Player;
import mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RunOutOfMoneyEventTest {

    private static final String NAME = "Horse";

    @Test
    void processing_the_event_makes_the_player_leaving_the_game(
            @Mock Game gameMock, @Mock Player playerMock) {

        when(playerMock.getName()).thenReturn(NAME);
        String expectedMessage = "Le joueur Horse quitte la partie car il n'a plus d'argent.";
        GameEvent gameEvent = new RunOutOfMoneyEvent(gameMock, playerMock);

        String actualMessage = gameEvent.process();

        verify(gameMock).removePlayer(playerMock);
        assertEquals(expectedMessage, actualMessage);

    }

}
