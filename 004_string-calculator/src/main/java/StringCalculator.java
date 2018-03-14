import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    /**
     * Constante définissant l'expression régulière qui vise à identifier les délimiteurs
     * initiaux séparant les nombres contenus dans la chaîne de caractères.
     */
    private static final String INITIAL_REGEX = ",|\n";

    /**
     * Constante définissant le début d'un délimiteur personnalisé.
     */
    private static final String BEG_OF_DELIMITER = "[";

    /**
     * Constante définissant la fin d'un délimiteur personnalisé.
     */
    private static final String END_OF_DELIMITER = "]";

    /**
     * Constante permettant l'ajout de nouveaux délimiteurs au sein de notre expression régulière initiale.
     */
    private static final String PIPE = "|";

    /**
     * Le StringCalculator permet de calculer la somme de nombres passés en argument sous la forme d'une chaîne
     * de caractères.
     *
     * @param input Suite de nombres sous forme d'une chaîne de caractères.
     * @return La somme des nombres positifs uniquement.
     */
    public static int add(String input) {

        // Si la chaîne d'entrée est vide, elle retourne 0 :
        if (StringUtils.isEmpty(input)) {
            return 0;
        }

        // Checkstyle n'apprécie pas de manipuler directement les arguments d'entrée :
        String numbersToParse = input;

        // Par défaut nous considérons que les nombres sont séparés par des RC et des virgules :
        StringBuilder regex = new StringBuilder(INITIAL_REGEX);

        // Toutefois, si nous sommes dans le cas des multiples délimiteurs personnalisés à longueur variable :
        if (numbersToParse.startsWith("//[")) {

            // Nous récupérons dans un premier temps les positions des tous premiers crochets :
            int indexOfCurrentBegOfDelimiter = numbersToParse.indexOf(BEG_OF_DELIMITER, 0);
            int indexOfCurrentEndOfDelimiter = numbersToParse.indexOf(END_OF_DELIMITER, 0);

            // Tant que nous trouvons des crochets au sein de la chaîne de caractères :
            while (indexOfCurrentBegOfDelimiter != -1 && indexOfCurrentEndOfDelimiter != -1) {

                // Nous préparons l'ajoute d'un délimiteur personnalisé :
                regex.append(PIPE);

                // Nous ajoutons le délimiteur (dont nous ne connaissons pas sa longueur) :
                regex.append(input.substring(indexOfCurrentBegOfDelimiter + 1, indexOfCurrentEndOfDelimiter));

                // Nous tentons de trouver la position du crochet ouvert qui suit le dernier crochet fermé :
                indexOfCurrentBegOfDelimiter = numbersToParse.indexOf(BEG_OF_DELIMITER, indexOfCurrentEndOfDelimiter);

                // Nous tentons de trouver la position du crochet fermé qui suit le dernier crochet ouvert :
                indexOfCurrentEndOfDelimiter = numbersToParse.indexOf(END_OF_DELIMITER, indexOfCurrentBegOfDelimiter);

            }

            // Nous isolons (du bloc dédié aux délimiteurs personnalisés à longueur variable) les nombres à parser :
            numbersToParse = numbersToParse.substring(input.lastIndexOf(END_OF_DELIMITER) + 2);

            // Mais si nous sommes dans le cas d'un unique délimiteur personnalisé à longeur fixe (1 seul caractère) :
        } else if (numbersToParse.startsWith("//")) {

            // Nous ajoutons ce délimiteur à l'expression régulière :
            regex.append(PIPE + input.substring(2, 3));

            // Nous isolons (du bloc dédié au délimiteur à longeur fixe) les nombres à parser :
            numbersToParse = numbersToParse.substring(4);

        }

        // Nous procédons à l'échappement des potentiels caractères qui pourraient causer un dysfonctionnement
        // de l'expression régulière puis dans un second temps nous isolons les nombres à sommer pour enfin
        // déduire la somme attendue :
        return checkSplitAndSum(numbersToParse, escapeCharacters(regex.toString()));

    }

    /**
     * Échappement des potentiels caractères qui pourrait causer un dysfonctionnement de l'expression régulière.
     *
     * @param regexToEscape Expression régulière dont il faut traiter les caractères en les échappant.
     * @return L'expression régulière corrigée.
     */
    private static String escapeCharacters(String regexToEscape) {

        return regexToEscape.replace("*", "\\*")
                .replace("%", "\\%")
                .replace("$", "\\$");

    }

    /**
     * Vérifie l'absence de nombres négatifs. Si aucun nombre positif n'a été
     * identifié, alors le système opère la somme des entiers inférieurs ou égaux à 1000.
     *
     * @param numbersToSum Nombres à découper puis à traiter.
     * @param delimiters   Délimiteurs à prendre en considération pour le découpage.
     * @return La somme des nombres n'excédant pas 1000, si aucun nombre positif n'a été détecté.
     */
    private static int checkSplitAndSum(String numbersToSum, String delimiters) {

        // Mets fin au traitement si au moin un nombre négatif a été identifié dans le stream :
        rejectNegativeNumbers(Arrays.stream(numbersToSum.split(delimiters)).filter(n -> Integer.parseInt(n) < 0)
                .collect(Collectors.toList()));

        String[] temp = numbersToSum.split(delimiters);

        // Procède à la somme des nombres positifs n'excédant pas 1000 :
        return Arrays.stream(numbersToSum.split(delimiters)).mapToInt(Integer::parseInt).filter(n -> n < 1000).sum();

    }

    private static void rejectNegativeNumbers(List<String> negativesNumbers) {
        if (negativesNumbers.size() == 1) {
            throw new IllegalArgumentException("negatives not allowed");
        } else if (negativesNumbers.size() > 1) {
            throw new IllegalArgumentException("negatives not allowed : " +
                    String.join(", ", negativesNumbers));
        }
    }

}
