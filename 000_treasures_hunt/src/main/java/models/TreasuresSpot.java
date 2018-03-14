package models;

import enumerations.EntityType;

public class TreasuresSpot extends Entity {

    static final String TO_STRING_TEMPLATE = "%s - %s - %s - %s";

    private int nbTreasures;

    public TreasuresSpot(Coordinates coordinates, int nbTreasures) {
        super(EntityType.T, coordinates);
        this.nbTreasures = nbTreasures;
    }

    public int getNbTreasures() {
        return nbTreasures;
    }

    public void setNbTreasures(int nbTreasures) {
        this.nbTreasures = nbTreasures;
    }

    /**
     * Decreases the number of treasures in the current spot when one treasure has been found by an
     * adventurer.
     */
    public void oneTreasureHasBeenFound() {
        if (this.nbTreasures > 0) {
            this.nbTreasures--;
        }
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_TEMPLATE, this.getEntityType().name(),
                this.getCoordinates().getAbscissa(), this.getCoordinates().getOrdinate(), this.nbTreasures);
    }

}
