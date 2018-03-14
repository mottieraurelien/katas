package models;

import enumerations.EntityType;

public class Entity {

    private EntityType entityType;
    private Coordinates coordinates;

    Entity(EntityType entityType, Coordinates coordinates) {
        this.entityType = entityType;
        this.coordinates = coordinates;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
