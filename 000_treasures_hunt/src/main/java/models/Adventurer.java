package models;

import enumerations.Direction;
import enumerations.EntityType;

public class Adventurer extends Entity {

    static final String TO_STRING_TEMPLATE = "%s - %s - %s - %s - %s - %s";

    private String firstname;
    private Direction direction;
    private String instructions;
    private int nbCollectedTreasures;

    /**
     * Constructor.
     *
     * @param firstname    Firstname of the adventurer.
     * @param coordinates  Initial position of the adventurer on the map.
     * @param direction    Initial direction of the adventurer on the map.
     * @param instructions Different moves of the adventurer on the map.
     */
    public Adventurer(Coordinates coordinates, String firstname, Direction direction,
                      String instructions) {
        super(EntityType.A, coordinates);
        this.firstname = firstname;
        this.direction = direction;
        this.instructions = instructions;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getNbCollectedTreasures() {
        return nbCollectedTreasures;
    }

    public void setNbCollectedTreasures(int nbCollectedTreasures) {
        this.nbCollectedTreasures = nbCollectedTreasures;
    }

    public void collectOneTreasureFromTheTreasuresSpot() {
        this.nbCollectedTreasures++;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_TEMPLATE, this.getEntityType().name(), this.firstname,
                this.getCoordinates().getAbscissa(), this.getCoordinates().getOrdinate(),
                this.direction.name(), this.nbCollectedTreasures);
    }

}
