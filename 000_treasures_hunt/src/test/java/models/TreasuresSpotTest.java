package models;

import enumerations.EntityType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * jUnit tests for {TreasuresSpot} class.
 */
public class TreasuresSpotTest {

    private static final int MINIMAL_NUMBER_OF_TREASURES = 0;
    private static final int NUMBER_OF_TREASURES = 7;
    private static final Coordinates TREASURES_SPOT_COORDINATES = new Coordinates(-1, -1);

    @Test
    public void constructorWithNullCoordinates() {

        // [Given] Not needed.

        // [When]
        TreasuresSpot treasuresSpot = new TreasuresSpot(null, NUMBER_OF_TREASURES);

        // [Then]
        assertEquals(EntityType.T, treasuresSpot.getEntityType());
        assertNull(treasuresSpot.getCoordinates());
        assertEquals(NUMBER_OF_TREASURES, treasuresSpot.getNbTreasures());

    }

    @Test
    public void constructorNominalFlow() {

        // [Given] Not needed.

        // [When]
        TreasuresSpot treasuresSpot =
                new TreasuresSpot(TREASURES_SPOT_COORDINATES, NUMBER_OF_TREASURES);

        // [Then]
        assertEquals(EntityType.T, treasuresSpot.getEntityType());
        assertEquals(TREASURES_SPOT_COORDINATES, treasuresSpot.getCoordinates());
        assertEquals(NUMBER_OF_TREASURES, treasuresSpot.getNbTreasures());

    }

    @Test
    public void toStringNominalFlow() {

        // [Given]
        String expected = String.format(TreasuresSpot.TO_STRING_TEMPLATE, EntityType.T,
                TREASURES_SPOT_COORDINATES.getAbscissa(), TREASURES_SPOT_COORDINATES.getOrdinate(),
                NUMBER_OF_TREASURES);
        TreasuresSpot treasuresSpot =
                new TreasuresSpot(TREASURES_SPOT_COORDINATES, NUMBER_OF_TREASURES);

        // [When / Then]
        assertEquals(expected, treasuresSpot.toString());

    }

    @Test
    public void oneTreasureHasBeenFoundOnAnEmptySpot() {

        // [Given]
        TreasuresSpot treasuresSpot =
                new TreasuresSpot(TREASURES_SPOT_COORDINATES, MINIMAL_NUMBER_OF_TREASURES);

        // [When]
        treasuresSpot.oneTreasureHasBeenFound();

        // [Then]
        assertEquals(MINIMAL_NUMBER_OF_TREASURES, treasuresSpot.getNbTreasures());

    }

    @Test
    public void oneTreasureHasBeenFoundWithSomeTreasuresToCollect() {

        // [Given]
        int expectedRemainingTreasures = NUMBER_OF_TREASURES - 1;
        TreasuresSpot treasuresSpot =
                new TreasuresSpot(TREASURES_SPOT_COORDINATES, NUMBER_OF_TREASURES);

        // [When]
        treasuresSpot.oneTreasureHasBeenFound();

        // [Then]
        assertEquals(expectedRemainingTreasures, treasuresSpot.getNbTreasures());

    }

    @Test
    public void getterSetterNominalFlow() {

        // [Given]
        TreasuresSpot treasuresSpot =
                new TreasuresSpot(TREASURES_SPOT_COORDINATES, MINIMAL_NUMBER_OF_TREASURES);

        // [When]
        treasuresSpot.setNbTreasures(NUMBER_OF_TREASURES);

        // [Then]
        assertEquals(NUMBER_OF_TREASURES, treasuresSpot.getNbTreasures());

    }

}
