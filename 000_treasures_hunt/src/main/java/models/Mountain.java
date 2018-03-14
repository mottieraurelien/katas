package models;

import enumerations.EntityType;

public class Mountain extends Entity {

    static final String TO_STRING_TEMPLATE = "%s - %s - %s";

    public Mountain(Coordinates coordinates) {
        super(EntityType.M, coordinates);
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_TEMPLATE, this.getEntityType().name(),
                this.getCoordinates().getAbscissa(), this.getCoordinates().getOrdinate());
    }

}
