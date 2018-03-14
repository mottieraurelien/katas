import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StringCalculatorTest {

    @Test
    public void givenAnEmptyStringReturnsZero() {

        // Arrange
        String input = "";
        int expected = 0;

        // Act
        int actual = StringCalculator.add(input);

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    public void given_one_number_as_string_returns_the_number() {

        String input = "10";
        int expected = 10;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    @Test
    public void given_two_numbers_as_string_returns_the_sum() {

        String input = "1,2";
        int expected = 3;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);


    }

    @Test
    public void given_an_unknown_amount_of_numbers_returns_the_sum() {

        String input = "2,3,4,5";
        int expected = 14;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    @Test
    public void given_new_line_character_behaves_as_a_separator() {

        String input = "1\n2,3";
        int expected = 6;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    @Test
    public void given_a_different_delimiter_as_a_separator() {

        String input = "//;\n1;2,3";
        int expected = 6;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    @Test
    public void given_a_negative_number_throws_an_exception() {

        String input = "-1";

        try {

            StringCalculator.add(input);
            fail("This test should have thrown an IllegalArgumentException.");

        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed", e.getMessage());
        }

    }

    @Test
    public void given_multiple_negative_numbers_throws_them_in_the_exception_message() {

        String input = "-1,-2";

        try {

            StringCalculator.add(input);
            fail("This test should have thrown an IllegalArgumentException.");

        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed : -1, -2", e.getMessage());
        }

    }

    @Test
    public void given_numbers_exceed_thousand_are_ignored() {

        String input = "2,1001";
        int expected = 2;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    @Test
    public void given_different_delimiters_with_various_length_as_a_separator() {

        String input = "//[***]\n1***2,3";
        int expected = 6;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    @Test
    public void given_two_delimiters_as_a_separator() {

        String input = "//[*][%]\n1*2%3";
        int expected = 6;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    @Test
    public void given_multiple_delimiters_with_various_length_as_a_separator() {

        String input = "//[***][$][%%]\n1***2%%1$3";
        int expected = 7;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    /**
     * Nous aurions pu aussi définir un test paramétisé pour représenter plus efficacement l'exhaustivité des cas
     * nominaux...
     */
    @ParameterizedTest
    @MethodSource("getScenariosForNominalFlows")
    void testNominalFlowsForAddMethod(String input, int expectedResultSum) {

        // [Act / Assert]
        assertEquals(expectedResultSum, StringCalculator.add(input));

    }

    /**
     * Établit l'exhaustivité des cas nominaux.
     *
     * @return Les arguments d'entrée lié à chaque scénario représentant un cas nominal.
     */
    private static Stream<Arguments> getScenariosForNominalFlows() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("10", 10),
                Arguments.of("1,2", 3),
                Arguments.of("2,3,4,5", 14),
                Arguments.of("1\n2,3", 6),
                Arguments.of("//;\n1;2,3", 6),
                Arguments.of("2,1001", 2),
                Arguments.of("//[***]\n1***2,3", 6),
                Arguments.of("//[*][%]\n1*2%3", 6),
                Arguments.of("//[***][$][%%]\n1***2%%1$3", 7)
        );
    }

}
