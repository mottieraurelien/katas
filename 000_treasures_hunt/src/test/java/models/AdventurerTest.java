package models;

import enumerations.Direction;
import enumerations.EntityType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * jUnit tests for {Adventurer} class.
 */
public class AdventurerTest {

    private static final Coordinates ADVENTURER_COORDINATES = new Coordinates(1, 1);
    private static final String FIRSTNAME = "Lara";
    private static final Direction DIRECTION = Direction.S;
    private static final String INSTRUCTIONS = "AADADAGGA";
    private static final int NB_COLLECTED_TREASURES = 2;

    @Test
    public void constructorWithNullParameters() {

        // [Given] Not needed.

        // [When]
        Adventurer adventurer = new Adventurer(null, null, null, null);

        // [Then]
        assertEquals(EntityType.A, adventurer.getEntityType());
        assertNull(adventurer.getCoordinates());
        assertNull(adventurer.getFirstname());
        assertNull(adventurer.getDirection());
        assertNull(adventurer.getInstructions());
        assertEquals(0, adventurer.getNbCollectedTreasures());

    }

    @Test
    public void constructorNominalFlow() {

        // [Given] Not needed.

        // [When]
        Adventurer adventurer =
                new Adventurer(ADVENTURER_COORDINATES, FIRSTNAME, DIRECTION, INSTRUCTIONS);

        // [Then]
        assertEquals(EntityType.A, adventurer.getEntityType());
        assertEquals(ADVENTURER_COORDINATES, adventurer.getCoordinates());
        assertEquals(FIRSTNAME, adventurer.getFirstname());
        assertEquals(DIRECTION, adventurer.getDirection());
        assertEquals(INSTRUCTIONS, adventurer.getInstructions());
        assertEquals(0, adventurer.getNbCollectedTreasures());

    }

    @Test
    public void gettersSettersNominalFlow() {

        // [Given]
        Adventurer adventurer = new Adventurer(null, null, null, null);

        // [When]
        adventurer.setCoordinates(ADVENTURER_COORDINATES);
        adventurer.setFirstname(FIRSTNAME);
        adventurer.setDirection(DIRECTION);
        adventurer.setInstructions(INSTRUCTIONS);
        adventurer.setNbCollectedTreasures(NB_COLLECTED_TREASURES);

        // [Then]
        assertEquals(ADVENTURER_COORDINATES, adventurer.getCoordinates());
        assertEquals(FIRSTNAME, adventurer.getFirstname());
        assertEquals(DIRECTION, adventurer.getDirection());
        assertEquals(INSTRUCTIONS, adventurer.getInstructions());
        assertEquals(NB_COLLECTED_TREASURES, adventurer.getNbCollectedTreasures());

    }

    @Test
    public void toStringNominalFlow() {

        // [Given]
        String expected = String.format(Adventurer.TO_STRING_TEMPLATE, EntityType.A, FIRSTNAME,
                ADVENTURER_COORDINATES.getAbscissa(), ADVENTURER_COORDINATES.getOrdinate(), DIRECTION, 0);
        Adventurer adventurer =
                new Adventurer(ADVENTURER_COORDINATES, FIRSTNAME, DIRECTION, INSTRUCTIONS);

        // [When / Then]
        assertEquals(expected, adventurer.toString());

    }

    @Test
    public void collectOneTreasureFromTheTreasuresSpotNominalFlow() {

        // [Given]
        Adventurer adventurer =
                new Adventurer(ADVENTURER_COORDINATES, FIRSTNAME, DIRECTION, INSTRUCTIONS);

        // [When]
        adventurer.collectOneTreasureFromTheTreasuresSpot();

        // [Then]
        assertEquals(1, adventurer.getNbCollectedTreasures());

    }

}
