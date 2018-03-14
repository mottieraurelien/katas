package commands;

import enumerations.Instruction;
import models.Adventurer;
import models.TreasuresMap;

public class MoveCommand implements EntityCommand {

    @Override
    public void execute(TreasuresMap treasuresMap, Adventurer adventurer, Instruction instruction) {

        // Basic test to check if the move method would require a true implementation :
        if (instruction != Instruction.A) {
            throw new UnsupportedOperationException(String.format("Direction %s unknown.", instruction));
        }

        // Calls the default implement behavior for this command :
        this.move(treasuresMap, adventurer);

    }

}
