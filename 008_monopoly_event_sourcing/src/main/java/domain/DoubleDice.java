package domain;

import java.util.Random;

public final class DoubleDice {

    private static final Random RANDOM = new Random();
    private final Integer maxValuePerDice;
    private Integer value;

    DoubleDice(final Integer maxValuePerDice) {
        this.maxValuePerDice = maxValuePerDice;
    }

    public Integer getValue() {
        return value;
    }

    public void roll() {
        Integer firstDiceRoll = RANDOM.nextInt(maxValuePerDice) + 1;
        Integer secondDiceRoll = RANDOM.nextInt(maxValuePerDice) + 1;
        this.value = firstDiceRoll + secondDiceRoll;
    }

}
