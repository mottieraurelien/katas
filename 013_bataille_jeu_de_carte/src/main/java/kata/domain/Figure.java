package kata.domain;

public enum Figure {

    NO_02(2),
    NO_03(3),
    NO_04(4),
    NO_05(5),
    NO_06(6),
    NO_07(7),
    NO_08(8),
    NO_09(9),
    NO_10(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    AS(14);

    private final int value;

    Figure(final int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

}