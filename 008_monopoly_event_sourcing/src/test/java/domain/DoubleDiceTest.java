package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleDiceTest {

    private static final Integer MIN_VALUE_FOR_ALL_DICES = 2;
    private static final Integer MAX_VALUE_FOR_ALL_DICES = 12;
    private static final Integer MAX_VALUE_PER_DICE = 6;

    @Test
    void rolling_a_double_dice_generates_a_number_between_two_and_twelve() {

        DoubleDice doubleDice = new DoubleDice(MAX_VALUE_PER_DICE);

        doubleDice.roll();

        assertTrue(doubleDice.getValue() >= MIN_VALUE_FOR_ALL_DICES
                && doubleDice.getValue() <= MAX_VALUE_FOR_ALL_DICES);

    }

}
