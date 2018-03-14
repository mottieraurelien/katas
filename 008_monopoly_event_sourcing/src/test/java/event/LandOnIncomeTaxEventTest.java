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
class LandOnIncomeTaxEventTest {

    private static final String NAME = "Horse";

    /**
     * The player will pay only 100$ because 20% of his net worth is less than 200$.
     * So his final balance will be 500$ - 100$ = 400$.
     */
    @Test
    void processing_the_event_makes_the_player_paying_the_tax_by_giving_twenty_percent_of_his_net_worth(
            @Mock Player playerMock) {

        BigDecimal inputBalance = new BigDecimal("500");
        BigDecimal expectedAmount = new BigDecimal("100.0");
        String expectedMessage = "Le joueur Horse paie sa taxe d'impôts sur le revenu.";
        when(playerMock.getName()).thenReturn(NAME);
        when(playerMock.getBalance()).thenReturn(inputBalance);
        GameEvent gameEvent = new LandOnIncomeTaxEvent(playerMock);

        String actualMessage = gameEvent.process();

        verify(playerMock).pay(expectedAmount);
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * The player will pay only 200$ because 20% of his net worth is way more than 200$.
     * So his final balance will be 2000$ - 200$ = 1800$.
     */
    @Test
    void processing_the_event_makes_the_player_paying_the_tax_by_giving_two_hundred_dollars(
            @Mock Player playerMock) {

        BigDecimal inputBalance = new BigDecimal("2000");
        BigDecimal expectedAmount = new BigDecimal("200");
        String expectedMessage = "Le joueur Horse paie sa taxe d'impôts sur le revenu.";
        when(playerMock.getName()).thenReturn(NAME);
        when(playerMock.getBalance()).thenReturn(inputBalance);
        GameEvent gameEvent = new LandOnIncomeTaxEvent(playerMock);

        String actualMessage = gameEvent.process();

        verify(playerMock).pay(expectedAmount);
        assertEquals(expectedMessage, actualMessage);

    }

}
