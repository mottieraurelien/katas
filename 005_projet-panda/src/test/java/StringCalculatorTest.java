import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    private static Stream<Arguments> getScenariosForGivenTwoNumbersSeparatedByAComma() {
        return Stream.of(
                Arguments.of(3, "1,2"),
                Arguments.of(5, "3,2")
        );
    }

    @Test
    public void given_an_empty_string_returns_zero() {

        String input = "";
        int expected = 0;

        int actual = StringCalculator.add(input);

        assertEquals(expected, actual);

    }

    @ParameterizedTest
    @CsvSource(value = {("1, 1"), ("2, 2")})
    public void given_a_number_returns_this_number(int expected, String input) {
        int actual = StringCalculator.add(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getScenariosForGivenTwoNumbersSeparatedByAComma")
    public void given_two_numbers_separated_by_a_comma_returns_their_sum(int expected, String input) {
        int actual = StringCalculator.add(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getScenariiForGivenManyNumbers")
    public void given_many_numbers_separated_by_a_comma_returns_their_sum(int expected, String input) {
        int actual = StringCalculator.add(input);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getScenariiForGivenManyNumbers() {
        return Stream.of(
                Arguments.of(15, "1,2,12"),
                Arguments.of(50, "3,2,20,10,15")
        );
    }

    @ParameterizedTest
    @CsvSource(value = {("6, '1\n2\n3'"), ("15, '2\n4\n9'"), ("14, '2\n3,9'")})
    public void given_numbers_separated_by_linefeed_returns_their_sum(int expected, String input) {
        int actual = StringCalculator.add(input);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {("6, '//;\n1;2;3'"), ("15, '//€\n2€4€9'")})
    public void given_numbers_separated_by_custom_separator_returns_their_sum(int expected, String input) {
        int actual = StringCalculator.add(input);
        assertEquals(expected, actual);
    }

}
