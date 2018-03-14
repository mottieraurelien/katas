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
class MoveToLocationEventTest {

    private static final String NAME = "Horse";

    @Test
    void processing_the_event_makes_the_player_moving_to_the_targeted_location(
            @Mock Player playerMock) {

        Integer inputTargetedLocation = 3;
        when(playerMock.getName()).thenReturn(NAME);
        Integer expectedLocation = 3;
        when(playerMock.getLocation()).thenReturn(3);
        String expectedMessage = "Le joueur Horse est arrivé à la case 3.";
        GameEvent gameEvent = new MoveToLocationEvent(playerMock, inputTargetedLocation);

        String actualMessage = gameEvent.process();

        verify(playerMock).moveTo(expectedLocation);
        assertEquals(expectedMessage, actualMessage);

    }

}
