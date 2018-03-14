package event;

import domain.Player;
import mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PassOverGoEventTest {

    private static final String NAME = "Horse";

    @Test
    void processing_the_event_makes_the_player_receiving_his_money(
            @Mock Player playerMock) {

        BigDecimal expectedAmount = new BigDecimal("200");
        String expectedMessage = "Le joueur Horse reçoit 200$ car il est toujours dans la partie.";
        when(playerMock.getName()).thenReturn(NAME);
        GameEvent gameEvent = new PassOverGoEvent(playerMock);

        String actualMessage = gameEvent.process();

        verify(playerMock).receive(expectedAmount);
        assertEquals(expectedMessage, actualMessage);

    }

}
