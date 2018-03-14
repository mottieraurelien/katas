package models;

import commands.CommandProvider;
import enumerations.EntityType;
import enumerations.Instruction;

import java.util.*;

public class TreasuresMap {

    private static final String TO_STRING_TEMPLATE = "%s - %s - %s";

    private Dimensions dimensions;
    private List<Mountain> mountains;
    private List<TreasuresSpot> treasuresSpots;
    private List<Adventurer> adventurers;
    private Map<Coordinates, List<Entity>> availableCoordinates;

    public TreasuresMap() {
        this.availableCoordinates = new HashMap<>();
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
        this.computesAvailableCoordinates();
    }

    public List<Mountain> getMountains() {
        return mountains;
    }

    public void setMountains(List<Mountain> mountains) {
        this.mountains = mountains;
        this.drawMountains();
    }

    public List<TreasuresSpot> getTreasuresSpots() {
        return treasuresSpots;
    }

    public void setTreasuresSpots(List<TreasuresSpot> treasuresSpots) {
        this.treasuresSpots = treasuresSpots;
        this.drawTreasuresSpots();
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public void setAdventurers(List<Adventurer> adventurers) {
        this.adventurers = adventurers;
        this.drawAdventurers();
    }

    public Map<Coordinates, List<Entity>> getAvailableCoordinates() {
        return availableCoordinates;
    }

    public void setAvailableCoordinates(Map<Coordinates, List<Entity>> availableCoordinates) {
        this.availableCoordinates = availableCoordinates;
    }

    /**
     * Runs adventurer's instructions sequentially.
     */
    public void makeTheAdventurersFindingTheTreasures() {

        // Looks over the found adventurers :
        adventurers.forEach(adventurer -> {

            // Looks over the sequential instructions to process for the current adventurer :
            adventurer.getInstructions().chars().mapToObj(i -> (char) i).forEach(c -> {

                // Identifies the current instruction :
                Instruction instruction = Instruction.valueOf(String.valueOf(c));

                // According to the type of the current instruction, executes the right command :
                CommandProvider.getCommandImplementation(instruction).execute(this, adventurer,
                        instruction);

            });

        });

    }

    private void computesAvailableCoordinates() {
        for (int r = 0; r <= this.dimensions.getMaxCoordinates().getAbscissa(); r++) {
            for (int c = 0; c <= this.dimensions.getMaxCoordinates().getOrdinate(); c++) {
                this.availableCoordinates.put(new Coordinates(r, c), new ArrayList<>());
            }
        }
    }

    private void drawMountains() {
        this.mountains.forEach(mountain -> this.availableCoordinates.put(mountain.getCoordinates(),
                new ArrayList<>(Collections.singletonList(mountain))));
    }

    private void drawTreasuresSpots() {
        this.treasuresSpots
                .forEach(treasuresSpot -> this.availableCoordinates.put(treasuresSpot.getCoordinates(),
                        new ArrayList<>(Collections.singletonList(treasuresSpot))));
    }

    private void drawAdventurers() {
        this.adventurers.forEach(adventurer -> this.availableCoordinates
                .put(adventurer.getCoordinates(), new ArrayList<>(Collections.singletonList(adventurer))));
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_TEMPLATE, EntityType.C.name(),
                this.getDimensions().getMaxCoordinates().getAbscissa(),
                this.getDimensions().getMaxCoordinates().getOrdinate());
    }

}
