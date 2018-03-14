package domain;

import exception.IncorrectAmountException;
import exception.IncorrectLocationException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {

    private static final String NAME = "Horse";
    private static final Integer EXISTENT_TARGETED_LOCATION = 39;
    private static final Integer NON_EXISTENT_TARGETED_LOCATION = 40;
    private static final BigDecimal AMOUNT = BigDecimal.TEN;
    private static final BigDecimal BALANCE = new BigDecimal("100");

    @Test
    void given_a_new_player_initializes_his_attributes_with_the_specified_input_parameters() {

        Player player = new Player(NAME, null, BALANCE);

        assertEquals(NAME, player.getName());
        assertEquals(BALANCE, player.getBalance());

    }

    @Test
    void given_a_positive_amount_to_give_to_the_player_increases_his_balance() {

        BigDecimal expectedBalance = new BigDecimal("110");
        Player player = new Player(NAME, null, BALANCE);

        player.receive(AMOUNT);

        assertEquals(expectedBalance, player.getBalance());

    }

    @Test
    void given_a_negative_amount_to_give_to_the_player_throws_an_IncorrectAmountException() {

        Player player = new Player(NAME, null, BALANCE);

        assertThrows(IncorrectAmountException.class, () -> player.receive(AMOUNT.negate()));

    }

    @Test
    void given_a_positive_amount_to_collect_from_the_player_decreases_his_balance() {

        BigDecimal expectedBalance = new BigDecimal("90");
        Player player = new Player(NAME, null, BALANCE);

        player.pay(AMOUNT);

        assertEquals(expectedBalance, player.getBalance());

    }

    @Test
    void given_a_negative_amount_to_collect_from_the_player_throws_an_IncorrectAmountException() {

        Player player = new Player(NAME, null, BALANCE);

        assertThrows(IncorrectAmountException.class, () -> player.pay(AMOUNT.negate()));

    }

    @Test
    void given_an_existent_targeted_location_makes_moving_the_player_to_this_specific_location() {

        Player player = new Player(NAME, null, null);

        player.moveTo(EXISTENT_TARGETED_LOCATION);

        assertEquals(EXISTENT_TARGETED_LOCATION, player.getLocation());

    }

    @Test
    void given_an_non_existent_targeted_location_does_not_make_moving_the_player_but_throws_an_IncorrectLocationException() {

        Player player = new Player(NAME, null, null);

        assertThrows(IncorrectLocationException.class, () -> player.moveTo(NON_EXISTENT_TARGETED_LOCATION));

    }

}
