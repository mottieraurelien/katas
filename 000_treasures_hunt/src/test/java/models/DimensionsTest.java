package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * jUnit tests for {Dimensions} class.
 */
public class DimensionsTest {

    private static final Coordinates MAX_COORDINATES = new Coordinates(-1, -1);

    @Test
    public void constructorWithNullParameters() {

        // [Given] Not needed.

        // [When]
        Dimensions dimensions = new Dimensions(null);

        // [Then]
        assertNull(dimensions.getMaxCoordinates());

    }

    @Test
    public void constructorNominalFlow() {

        // [Given] Not needed.

        // [When]
        Dimensions dimensions = new Dimensions(MAX_COORDINATES);

        // [Then]
        assertEquals(MAX_COORDINATES, dimensions.getMaxCoordinates());

    }

    @Test
    public void gettersSettersNominalFlow() {

        // [Given]
        Dimensions dimensions = new Dimensions(null);

        // [When]
        dimensions.setMaxCoordinates(MAX_COORDINATES);

        // [Then]
        assertEquals(MAX_COORDINATES, dimensions.getMaxCoordinates());

    }

}
