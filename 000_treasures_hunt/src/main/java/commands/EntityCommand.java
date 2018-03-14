package commands;

import enumerations.EntityType;
import enumerations.Instruction;
import models.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface EntityCommand {

    /**
     * Execute the command.
     */
    void execute(TreasuresMap treasuresMap, Adventurer adventurer, Instruction instruction);

    /**
     * Default implemented behavior to make an adventurer moving on the treasures map according to its
     * current orientation. This default behavior is the only needed implemented to make him moving
     * forward.
     *
     * @param treasuresMap The treasures map.
     * @param adventurer   The adventurer to move on the treasures map.
     */
    default void move(TreasuresMap treasuresMap, Adventurer adventurer) {

        // Computes the next coordinate of the adventurer according to its current orientation :
        Coordinates nextCoordinates = computesNextCoordinatesWhenMovingForward(adventurer);

        // Checks if the adventurer can really move (still on the treasures map + no mountain + no
        // existing adventurer on the next coordinates) :
        if (canMove(treasuresMap.getAvailableCoordinates(), nextCoordinates)) {

            // Retrieves the current entities placed on these next coordinates (could be a free treasures
            // spot) :
            List<Entity> existingEntities = treasuresMap.getAvailableCoordinates().get(nextCoordinates);

            // Among the current entities placed on these next coordinates, we need to know if the current
            // entity is a treasures spot (we have already checked that there is no adventurer that is
            // already on the treasures spot) :
            Optional<Entity> treasuresSpotOptional =
                    existingEntities.stream().filter(e -> EntityType.T == e.getEntityType()).findFirst();

            // Before moving, he leaves its last place :
            treasuresMap.getAvailableCoordinates().get(adventurer.getCoordinates())
                    .removeIf(e -> e.equals(adventurer));

            // If its last place was on a treasures spot :
            if (treasuresSpotOptional.isPresent()) {

                // Casts the treasures spot to manipulate it :
                TreasuresSpot treasuresSpot = (TreasuresSpot) treasuresSpotOptional.get();

                // The adventurer collects one of the treasures from the treasures spot :
                adventurer.collectOneTreasureFromTheTreasuresSpot();
                treasuresSpot.oneTreasureHasBeenFound();

                // If there is no treasures from the treasures spot anymore :
                if (treasuresSpot.getNbTreasures() == 0) {

                    // Removes the empty treasures spot from the treasures map :
                    treasuresMap.getAvailableCoordinates().get(treasuresSpot.getCoordinates())
                            .removeIf(e -> e.equals(treasuresSpot));
                    treasuresMap.getTreasuresSpots().remove(treasuresSpot);

                }

            }

            // And moves to the next coordinates :
            adventurer.setCoordinates(nextCoordinates);
            treasuresMap.getAvailableCoordinates().get(nextCoordinates).add(adventurer);

        }

    }

    /**
     * Computes the next coordinates in simulating the move (default behavior = moving forward) of the
     * adventurer.
     *
     * @param adventurer The adventurer to move.
     * @return The next coordinates.
     */
    default Coordinates computesNextCoordinatesWhenMovingForward(Adventurer adventurer) {

        // We base the next coordinates computing on its current ones :
        Coordinates nextCoordinates = new Coordinates(adventurer.getCoordinates().getAbscissa(),
                adventurer.getCoordinates().getOrdinate());

        // According to its current direction :
        switch (adventurer.getDirection()) {
            case N:
                nextCoordinates.setOrdinate(nextCoordinates.getOrdinate() - 1);
                break;
            case E:
                nextCoordinates.setAbscissa(nextCoordinates.getAbscissa() + 1);
                break;
            case O:
                nextCoordinates.setAbscissa(nextCoordinates.getAbscissa() - 1);
                break;
            case S:
                nextCoordinates.setOrdinate(nextCoordinates.getOrdinate() + 1);
                break;
            default:
                throw new UnsupportedOperationException(
                        String.format("Direction %s unknown.", adventurer.getDirection()));
        }

        return nextCoordinates;

    }

    /**
     * Checks if the adventurer can really move : he must be still on the treasures map (not outside)
     * and he won't cross any mountain and he won't be over an existing adventurer. We also assume
     * that we have already checked that the next coordinates of this adventurer is different from its
     * existing ones.
     *
     * @param availableCoordinates      All coordinates that the adventurer could go on.
     * @param nextCoordinatesAdventurer Coordinates where the adventurer want to move.
     * @return TRUE if the adventurer can move to the next wanted coordinates on the treasures map.
     * FALSE if he can't.
     */
    default boolean canMove(Map<Coordinates, List<Entity>> availableCoordinates,
                            Coordinates nextCoordinatesAdventurer) {

        // He must be still on the treasures map (not outside) :
        if (availableCoordinates.containsKey(nextCoordinatesAdventurer)) {

            // Retrieves the existing entities placed on the next coordinates of the adventurer :
            List<Entity> entitiesAlreadyPlacedOnNextCoordinates =
                    availableCoordinates.get(nextCoordinatesAdventurer);

            // If there is not any entity on the next coordinates :
            if (entitiesAlreadyPlacedOnNextCoordinates.stream().allMatch(Objects::isNull)) {
                return true;
            }

            // He won't be over any mountain :
            if (entitiesAlreadyPlacedOnNextCoordinates.stream()
                    .anyMatch(e -> EntityType.M == e.getEntityType())) {
                return false;
            }

            // He won't be over an existing adventurer (whatever if he is alone on these next coordinates
            // or if he has collected a treasure from the treasures spot) :
            return entitiesAlreadyPlacedOnNextCoordinates.stream()
                    .noneMatch(e -> EntityType.A == e.getEntityType());

        }

        // Won't be anymore on the treasures map :
        return false;

    }

}
