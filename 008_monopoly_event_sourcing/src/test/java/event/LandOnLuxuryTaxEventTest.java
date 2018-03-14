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
class LandOnLuxuryTaxEventTest {

    private static final String NAME = "Horse";

    /**
     * The player will pay 75$ whatever his actual net worth.
     * So his final balance will be 100$ - 75$ = 25$.
     */
    @Test
    void processing_the_event_makes_the_player_paying_the_tax_by_giving_seventy_five_dollars(
            @Mock Player playerMock) {

        BigDecimal inputBalance = new BigDecimal("100");
        BigDecimal expectedAmount = new BigDecimal("75");
        String expectedMessage = "Le joueur Horse paie sa taxe de luxe.";
        when(playerMock.getName()).thenReturn(NAME);
        when(playerMock.getBalance()).thenReturn(inputBalance);
        GameEvent gameEvent = new LandOnLuxuryTaxEvent(playerMock);

        String actualMessage = gameEvent.process();

        verify(playerMock).pay(expectedAmount);
        assertEquals(expectedMessage, actualMessage);

    }

}