import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum BoardSquares {

    DEPART("Départ", Collections.singletonList(0)),
    BOULEVARD_DE_BELLEVILLE("Boulevard de Belleville", Collections.singletonList(1)),
    CAISSE_DE_COMMUNAUTE("Caisse de Communauté", Arrays.asList(2, 17, 33)),
    RUE_LECOURBE("Rue Lecourbe", Collections.singletonList(3)),
    IMPOTS_SUR_LE_REVENU("Impôts sur le revenu", Collections.singletonList(4)),
    GARE_MONTPARNASSE("Gare Montparnasse", Collections.singletonList(5)),
    RUE_DE_VAUGIRARD("Rue de Vaugirard", Collections.singletonList(6)),
    CHANCE("Chance", Arrays.asList(7, 22, 36)),
    RUE_DE_COURCELLES("Rue de Courcelles", Collections.singletonList(8)),
    AVENUE_DE_LA_REPUBLIQUE("Avenue de la République", Collections.singletonList(9)),
    PRISON_OU_SIMPLE_VISITE("Prison / Simple visite", Collections.singletonList(10)),
    BOULEVARD_DE_LA_VILLETTE("Boulevard de la Vilette", Collections.singletonList(11)),
    CIE_DE_DISTRIBUTION_ELECTRICITE("Compagnie de distribution d'électricité", Collections.singletonList(12)),
    AVENUE_DE_NEUILLY("Avenue de Neuilly", Collections.singletonList(13)),
    RUE_DE_PARADIS("Rue du Paradis", Collections.singletonList(14)),
    GARE_DE_LYON("Gare de Lyon", Collections.singletonList(15)),
    AVENUE_MOZART("Avenue Mozart", Collections.singletonList(16)),
    BOULEVARD_SAINT_MICHEL("Boulevard Saint-Michel", Collections.singletonList(18)),
    PLACE_PIGALLE("Place Pigalle", Collections.singletonList(19)),
    PARC_GRATUIT("Parc gratuit", Collections.singletonList(20)),
    AVENUE_MATIGNON("Avenue Matignon", Collections.singletonList(21)),
    BOULEVARD_MALESHERBES("Boulevard Malesherbes", Collections.singletonList(23)),
    AVENUE_HENRI_MARTIN("Avenue Henri-Martin", Collections.singletonList(24)),
    GARE_DU_NORD("Gare du Nord", Collections.singletonList(25)),
    FAUBOURG_SAINT_HONORE("Faubourg Saint-Honoré", Collections.singletonList(26)),
    PLACE_DE_LA_BOURSE("Place de la Bourse", Collections.singletonList(27)),
    CIE_DE_DISTRIBUTION_DES_EAUX("Compagnie de distribution des eaux", Collections.singletonList(28)),
    RUE_LA_FAYETTE("Rue La Fayette", Collections.singletonList(29)),
    ALLEZ_EN_PRISON("Allez en prison", Collections.singletonList(30)),
    AVENUE_DE_BRETEUIL("Avenue de Breteuil", Collections.singletonList(31)),
    AVENUE_FOCH("Avenue Foch", Collections.singletonList(32)),
    BOULEVARD_DES_CAPUCINES("Boulevard des Capucines", Collections.singletonList(34)),
    GARE_SAINT_LAZARE("Gare Saint-Lazare", Collections.singletonList(35)),
    AVENUE_DES_CHAMPS_ELYSEES("Avenue des Champs-Elysées", Collections.singletonList(37)),
    TAXE_DE_LUXE("Taxe de luxe", Collections.singletonList(38)),
    RUE_DE_LA_PAIX("Rue de la Paix", Collections.singletonList(39));

    private String label;

    private List<Integer> indexes;

    BoardSquares(String label, List<Integer> indexes) {
        this.label = label;
        this.indexes = indexes;
    }

    public static BoardSquares toEnum(Integer index) {
        return Stream.of(values()).filter(s -> s.getIndexes().contains(index)).findFirst().orElse(null);
    }

    public static Integer getMaxNumber() {
        return Stream.of(values())
                .flatMap(boardSquares -> boardSquares.getIndexes().stream())
                .collect(Collectors.toList())
                .size();
    }

    public String getLabel() {
        return label;
    }

    public List<Integer> getIndexes() {
        return Collections.unmodifiableList(indexes);
    }

}
