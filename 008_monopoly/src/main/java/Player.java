import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.stream.IntStream;

public class Player {

    private static final Integer FIRST_LOCATION = 0;
    private static final Random RANDOM = new Random();
    private static final DecimalFormat DF = new DecimalFormat("0");
    private static final BigDecimal BONUS_WHEN_STILL_PLAYING = new BigDecimal("200");
    private static final BigDecimal LUXURY_TAX_REGULAR = new BigDecimal("75");
    private static final BigDecimal INCOME_TAX_REGULAR = new BigDecimal("200");
    private static final BigDecimal INCOME_TAX_RATE = new BigDecimal("0.8");
    private static final BigDecimal INCOME_TAX_BRINK = new BigDecimal("1000");

    private final String name;
    private Integer location;
    private BigDecimal balance;
    private Integer playedRounds;
    private Boolean inJail;

    Player(String name, BigDecimal balance) {
        this.name = name;
        this.inJail = Boolean.FALSE;
        this.balance = balance;
        this.playedRounds = 0;
        this.location = FIRST_LOCATION;
    }

    public Integer getLocation() {
        return location;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getPlayedRounds() {
        return this.playedRounds;
    }

    public void play() {

        Integer steps = this.rollDice();

        IntStream.iterate(1, n -> n + 1).limit(steps).forEach(n -> {
            this.location++;
            if (BoardSquares.getMaxNumber().equals(this.location)) {
                this.location = 0;
                this.balance = BONUS_WHEN_STILL_PLAYING.add(this.balance);
            }
        });

        BoardSquares playerSquare = BoardSquares.toEnum(this.location);
        switch (playerSquare) {
            case ALLEZ_EN_PRISON:
                this.location = BoardSquares.PRISON_OU_SIMPLE_VISITE.getIndexes().get(0);
                break;
            case IMPOTS_SUR_LE_REVENU:
                if (INCOME_TAX_BRINK.compareTo(this.balance) < 0) {
                    this.balance = this.balance.subtract(INCOME_TAX_REGULAR);
                } else {
                    this.balance = INCOME_TAX_RATE.multiply(this.balance);
                }
                break;
            case TAXE_DE_LUXE:
                this.balance = this.balance.subtract(LUXURY_TAX_REGULAR);
                break;
            default:
                break;
        }

        this.playedRounds++;

        System.out.println(String.format("%s rolled the dice, got %s, moved to %s and now have $%s",
                this.name, steps, BoardSquares.toEnum(this.location).getLabel(), DF.format(this.balance)));

    }

    Integer rollDice() {
        return RANDOM.nextInt(6) + 1;
    }

    public Boolean isInJail() {
        return inJail;
    }

}
