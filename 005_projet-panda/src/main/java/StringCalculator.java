import java.util.stream.Stream;

public class StringCalculator {

    private static final String DEFAULT_SEPARATORS = ",|\n";
    private static final int DEFAULT_VALUE = 0;
    private static final int CUSTOM_SEPARATOR_INDEX = 2;
    private static final String CUSTOM_SEPARATOR_PREFIX = "//";
    private static final int NUMBERS_START_INDEX = 4;

    public static int add(String input) {
        if(input.isEmpty()){
            return DEFAULT_VALUE;
        }

        String[] numbersAsString = getNumbersAsStrings(input);

        return Stream.of(numbersAsString)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private static String[] getNumbersAsStrings(String input) {
        if (!input.startsWith(CUSTOM_SEPARATOR_PREFIX)) {
            return input.split(DEFAULT_SEPARATORS);
        }

        char customSeparator = input.charAt(CUSTOM_SEPARATOR_INDEX);
        String separators = DEFAULT_SEPARATORS + "|" + customSeparator;
        return input.substring(NUMBERS_START_INDEX).split(separators);
    }
}
