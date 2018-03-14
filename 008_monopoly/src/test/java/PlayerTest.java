import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    @DisplayName("User Story 101 : All players start on the first location.")
    void a_new_player_starts_at_the_first_location() {

        BigDecimal inputBalance = BigDecimal.ZERO;
        BigDecimal expectedBalance = BigDecimal.ZERO;
        Integer expectedLocation = 0;
        Integer expectedNumberOfPlayedRounds = 0;

        Player player = new Player("Horse", inputBalance);

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @Test
    @DisplayName("User Story 101 : Player on beginning location (numbered 0), rolls 7, ends up on location 7.")
    void a_round_makes_the_player_moving_to_the_right_location() {

        BigDecimal inputBalance = BigDecimal.ZERO;
        BigDecimal expectedBalance = BigDecimal.ZERO;
        Integer expectedLocation = 7;
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return expectedLocation;
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @ParameterizedTest
    @CsvSource({"39, 6, 5", "10, 6, 16"})
    @DisplayName("User Story 101 : Player on location numbered 39, rolls 6, ends up on location 5.")
    void given_a_number_of_steps_and_an_initial_location_then_moves_the_player_to_the_right_location(
            Integer initialNumberOfSteps, Integer inputSteps, Integer expectedLocation) {

        BigDecimal inputBalance = BigDecimal.ZERO;
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Car", inputBalance) {
            @Override
            Integer rollDice() {
                return initialNumberOfSteps + inputSteps;
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @ParameterizedTest
    @CsvSource({"1, 1", "2, 2", "3, 3", "4, 4", "5, 5", "6, 6"})
    @DisplayName("User Story 201 : During a turn a player lands on some \"normal\" location and his balance does not" +
            "change.")
    void a_player_lands_on_a_normal_location_does_not_make_change_his_balance(
            Integer inputSteps, Integer expectedLocation) {

        BigDecimal inputBalance = BigDecimal.ZERO;
        BigDecimal expectedBalance = BigDecimal.ZERO;
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return inputSteps;
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @Test
    @DisplayName("User Story 201 : During a turn a player lands on Go and his balance increases by $200.")
    void a_player_lands_on_go_makes_increasing_his_balance_by_two_hundred_dollars() {

        BigDecimal inputBalance = BigDecimal.ZERO;
        BigDecimal expectedBalance = new BigDecimal(200);
        Integer inputSteps = BoardSquares.getMaxNumber();
        Integer expectedLocation = BoardSquares.DEPART.getIndexes().get(0);
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return inputSteps;
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @ParameterizedTest
    @CsvSource({"41, 1", "42, 2", "43, 3", "45, 5", "46, 6"})
    @DisplayName("User Story 202 : During a turn a player passes over Go and his balance increases by $200.")
    void a_player_passes_over_go_makes_increasing_his_balance_by_two_hundred_dollars(
            Integer inputSteps, Integer expectedLocation) {

        BigDecimal inputBalance = BigDecimal.ZERO;
        BigDecimal expectedBalance = new BigDecimal(200);
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return inputSteps;
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @Test
    @DisplayName("User Story 203 : Player starts before Go To Jail, lands on Go To Jail, ends up on Just Visiting and" +
            "his balance is unchanged.")
    void a_player_starts_before_go_to_jail_then_lands_ongo_to_jail_so_ends_up_on_just_visiting() {

        BigDecimal inputBalance = BigDecimal.ZERO;
        BigDecimal expectedBalance = BigDecimal.ZERO;
        Integer expectedLocation = BoardSquares.PRISON_OU_SIMPLE_VISITE.getIndexes().get(0);
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return BoardSquares.ALLEZ_EN_PRISON.getIndexes().get(0);
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @Test
    @DisplayName("User Story 204 : Player takes a turn and lands on Income Tax. His balance decreases by $200, which is" +
            "smaller than the 20% of his net worth.")
    void a_rich_player_lands_on_income_tax_pays_only_two_hundred_dollars() {

        BigDecimal inputBalance = new BigDecimal(2000);
        BigDecimal expectedBalance = new BigDecimal(1800);
        Integer expectedLocation = BoardSquares.IMPOTS_SUR_LE_REVENU.getIndexes().get(0);
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return BoardSquares.IMPOTS_SUR_LE_REVENU.getIndexes().get(0);
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @Test
    @DisplayName("User Story 204 : Player takes a turn and lands on Income Tax. His balance decreases by 20%," +
            "which is smaller than $200.")
    void a_poor_player_lands_on_income_tax_pays_twenty_percents_of_his_net_worth() {

        BigDecimal inputBalance = new BigDecimal(100);
        BigDecimal expectedBalance = new BigDecimal(80);
        Integer expectedLocation = BoardSquares.IMPOTS_SUR_LE_REVENU.getIndexes().get(0);
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return BoardSquares.IMPOTS_SUR_LE_REVENU.getIndexes().get(0);
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @Test
    @DisplayName("User Story 204 : Player passes Income Tax during a turn. Their balance is unchanged.")
    void a_player_passes_over_income_tax_does_not_make_change_his_balance() {

        BigDecimal inputBalance = new BigDecimal(80);
        BigDecimal expectedBalance = new BigDecimal(80);
        Integer expectedLocation = BoardSquares.GARE_MONTPARNASSE.getIndexes().get(0);
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return BoardSquares.GARE_MONTPARNASSE.getIndexes().get(0);
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @Test
    @DisplayName("User Story 205 : Player takes a turn and lands on Luxury Tax. Their balance decreases by $75.")
    void a_player_lands_on_luxury_tax_pays_seventy_five_dollars() {

        BigDecimal inputBalance = new BigDecimal(100);
        BigDecimal expectedBalance = new BigDecimal(25);
        Integer expectedLocation = BoardSquares.TAXE_DE_LUXE.getIndexes().get(0);
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return BoardSquares.TAXE_DE_LUXE.getIndexes().get(0);
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

    @Test
    @DisplayName("User Story 205 : Player passes Luxury Tax during a turn. Their balance is unchanged.")
    void a_player_passes_over_luxury_tax_does_not_make_change_his_balance() {

        BigDecimal inputBalance = new BigDecimal(75);
        BigDecimal expectedBalance = new BigDecimal(75);
        Integer expectedLocation = BoardSquares.RUE_DE_LA_PAIX.getIndexes().get(0);
        Integer expectedNumberOfPlayedRounds = 1;
        Player player = new Player("Horse", inputBalance) {
            @Override
            Integer rollDice() {
                return BoardSquares.RUE_DE_LA_PAIX.getIndexes().get(0);
            }
        };

        player.play();

        assertEquals(Boolean.FALSE, player.isInJail());
        assertEquals(expectedLocation, player.getLocation());
        assertEquals(0, expectedBalance.compareTo(player.getBalance()));
        assertEquals(expectedNumberOfPlayedRounds, player.getPlayedRounds());

    }

}
