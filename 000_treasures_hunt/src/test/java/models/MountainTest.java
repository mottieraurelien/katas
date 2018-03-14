package models;

import enumerations.EntityType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * jUnit tests for {Mountain} class.
 */
public class MountainTest {

    private static final Coordinates MOUNTAIN_COORDINATES = new Coordinates(-1, -1);

    @Test
    public void constructorWithNullCoordinates() {

        // [Given] Not needed.

        // [When]
        Mountain mountain = new Mountain(null);

        // [Then]
        assertEquals(EntityType.M, mountain.getEntityType());
        assertNull(mountain.getCoordinates());

    }

    @Test
    public void constructorNominalFlow() {

        // [Given] Not needed.

        // [When]
        Mountain mountain = new Mountain(MOUNTAIN_COORDINATES);

        // [Then]
        assertEquals(EntityType.M, mountain.getEntityType());
        assertEquals(MOUNTAIN_COORDINATES, mountain.getCoordinates());

    }

    @Test
    public void toStringNominalFlow() {

        // [Given]
        String expected = String.format(Mountain.TO_STRING_TEMPLATE, EntityType.M.name(),
                MOUNTAIN_COORDINATES.getAbscissa(), MOUNTAIN_COORDINATES.getOrdinate());
        Mountain mountain = new Mountain(MOUNTAIN_COORDINATES);

        // [When / Then]
        assertEquals(expected, mountain.toString());

    }

}
