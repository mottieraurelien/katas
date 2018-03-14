package models;

import enumerations.EntityType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * jUnit tests for {Entity} class.
 */
public class EntityTest {

    private static final EntityType ENTITY_TYPE = EntityType.A;
    private static final Coordinates ENTITY_COORDINATES = new Coordinates(-1, -1);

    @Test
    public void constructorWithNullParameters() {

        // [Given] Not needed.

        // [When]
        Entity entity = new Entity(null, null);

        // [Then]
        assertNull(entity.getEntityType());
        assertNull(entity.getCoordinates());

    }

    @Test
    public void constructorNominalFlow() {

        // [Given] Not needed.

        // [When]
        Entity entity = new Entity(ENTITY_TYPE, ENTITY_COORDINATES);

        // [Then]
        assertEquals(ENTITY_TYPE, entity.getEntityType());
        assertEquals(ENTITY_COORDINATES, entity.getCoordinates());

    }

    @Test
    public void gettersSettersNominalFlow() {

        // [Given]
        Entity entity = new Entity(null, null);

        // [When]
        entity.setEntityType(ENTITY_TYPE);
        entity.setCoordinates(ENTITY_COORDINATES);

        // [Then]
        assertEquals(ENTITY_TYPE, entity.getEntityType());
        assertEquals(ENTITY_COORDINATES, entity.getCoordinates());

    }

}
