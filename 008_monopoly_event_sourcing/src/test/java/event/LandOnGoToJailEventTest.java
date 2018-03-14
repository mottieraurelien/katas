package event;

import domain.Player;
import mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LandOnGoToJailEventTest {

    private static final String NAME = "Horse";

    @Test
    void processing_the_event_makes_the_player_moving_to_the_just_visiting_square(
            @Mock Player playerMock) {

        Integer expectedLocation = 10;
        String expectedMessage = "Le joueur Horse est envoyé à la case \"Simple visite\".";
        when(playerMock.getName()).thenReturn(NAME);
        GameEvent gameEvent = new LandOnGoToJailEvent(playerMock);

        String actualMessage = gameEvent.process();

        verify(playerMock).moveTo(expectedLocation);
        assertEquals(expectedMessage, actualMessage);

    }

}
