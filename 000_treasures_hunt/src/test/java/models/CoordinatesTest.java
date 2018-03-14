package models;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * jUnit tests for {Coordinates} class.
 */
public class CoordinatesTest {

    private static final int MINIMAL_ABSCISSA = 0;
    private static final int MINIMAL_ORDINATE = 0;
    private static final int ABSCISSA = 1;
    private static final int ORDINATE = 1;

    @Test
    public void constructorNominalFlow() {

        // [Given] Not needed.

        // [When]
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [Then]
        assertEquals(ABSCISSA, coordinates.getAbscissa());
        assertEquals(ORDINATE, coordinates.getOrdinate());

    }

    @Test
    public void gettersSettersNominalFlow() {

        // [Given]
        Coordinates coordinates = new Coordinates(MINIMAL_ABSCISSA, MINIMAL_ORDINATE);

        // [When]
        coordinates.setAbscissa(ABSCISSA);
        coordinates.setOrdinate(ORDINATE);

        // [Then]
        assertEquals(ABSCISSA, coordinates.getAbscissa());
        assertEquals(ORDINATE, coordinates.getOrdinate());

    }

    /**
     * Could have been written as a {Parameterized}.
     */
    @Test
    public void hashcodeNominalFlow() {

        // [Given / When / Then]
        Coordinates coordinatesOne = new Coordinates(MINIMAL_ABSCISSA, MINIMAL_ORDINATE);
        assertEquals(Objects.hash(MINIMAL_ABSCISSA, MINIMAL_ORDINATE), coordinatesOne.hashCode());

        Coordinates coordinatesTwo = new Coordinates(MINIMAL_ABSCISSA, ORDINATE);
        assertEquals(Objects.hash(MINIMAL_ABSCISSA, ORDINATE), coordinatesTwo.hashCode());

        Coordinates coordinatesThree = new Coordinates(ABSCISSA, MINIMAL_ORDINATE);
        assertEquals(Objects.hash(ABSCISSA, MINIMAL_ORDINATE), coordinatesThree.hashCode());

        Coordinates coordinatesFour = new Coordinates(ABSCISSA, ORDINATE);
        assertEquals(Objects.hash(ABSCISSA, ORDINATE), coordinatesFour.hashCode());

        // [Then] to make sure that coordinates are really unique :
        assertFalse(coordinatesOne.equals(coordinatesTwo));
        assertFalse(coordinatesOne.equals(coordinatesThree));
        assertFalse(coordinatesOne.equals(coordinatesFour));
        assertFalse(coordinatesTwo.equals(coordinatesThree));
        assertFalse(coordinatesTwo.equals(coordinatesFour));
        assertFalse(coordinatesThree.equals(coordinatesFour));

    }

    @Test
    public void toStringNominalFlow() {

        // [Given]
        String expectedToStringResult =
                String.format(Coordinates.TO_STRING_TEMPLATE, ABSCISSA, ORDINATE);
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [When / Then]
        assertEquals(expectedToStringResult, coordinates.toString());

    }

    @Test
    public void equalsWhenComparingWithNullObject() {

        // [Given]
        Coordinates nullCoordinates = null;
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [When / Then]
        assertFalse(coordinates.equals(nullCoordinates));

    }

    @Test
    public void equalsWhenComparingItSelf() {

        // [Given]
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [When / Then]
        assertTrue(coordinates.equals(coordinates));

    }

    @Test
    public void equalsWhenComparingAnObjectOfDifferentType() {

        // [Given]
        Object object = new Object();
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [When / Then]
        assertFalse(coordinates.equals(object));

    }

    @Test
    public void equalsWhenDifferentAbscissa() {

        // [Given]
        Coordinates coordinatesWithDifferentAbscissa = new Coordinates(MINIMAL_ABSCISSA, ORDINATE);
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [When / Then]
        assertFalse(coordinates.equals(coordinatesWithDifferentAbscissa));

    }

    @Test
    public void equalsWhenDifferentOrdinate() {

        // [Given]
        Coordinates coordinatesWithDifferentAbscissa = new Coordinates(ABSCISSA, MINIMAL_ORDINATE);
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [When / Then]
        assertFalse(coordinates.equals(coordinatesWithDifferentAbscissa));

    }

    @Test
    public void equalsWhenDifferentAbscissaAndOrdinate() {

        // [Given]
        Coordinates coordinatesWithDifferentAbscissa =
                new Coordinates(MINIMAL_ABSCISSA, MINIMAL_ORDINATE);
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [When / Then]
        assertFalse(coordinates.equals(coordinatesWithDifferentAbscissa));

    }

    @Test
    public void equalsWhenSameAbscissaAndOrdinate() {

        // [Given]
        Coordinates coordinatesWithDifferentAbscissa = new Coordinates(ABSCISSA, ORDINATE);
        Coordinates coordinates = new Coordinates(ABSCISSA, ORDINATE);

        // [When / Then]
        assertTrue(coordinates.equals(coordinatesWithDifferentAbscissa));

    }

}
