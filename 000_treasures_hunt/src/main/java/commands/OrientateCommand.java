package commands;

import enumerations.Direction;
import enumerations.Instruction;
import models.Adventurer;
import models.TreasuresMap;

public class OrientateCommand implements EntityCommand {

    @Override
    public void execute(TreasuresMap treasuresMap, Adventurer adventurer, Instruction instruction) {

        if (instruction == Instruction.G) {
            this.orientateOnLeft(adventurer);
        } else if (instruction == Instruction.D) {
            this.orientateOnRight(adventurer);
        }

    }

    /**
     * Orientate the adventurer on its right side according to its current direction.
     *
     * @param adventurer The adventurer to orientate to its new direction.
     */
    private void orientateOnLeft(Adventurer adventurer) {
        switch (adventurer.getDirection()) {
            case N:
                adventurer.setDirection(Direction.O);
                break;
            case E:
                adventurer.setDirection(Direction.N);
                break;
            case O:
                adventurer.setDirection(Direction.S);
                break;
            case S:
                adventurer.setDirection(Direction.E);
                break;
            default:
                throw new EnumConstantNotPresentException(adventurer.getDirection().getDeclaringClass(),
                        String.format("Direction %s unknown.", adventurer.getDirection()));
        }
    }

    /**
     * Orientate the adventurer on its right side according to its current direction.
     *
     * @param adventurer The adventurer to orientate to its new direction.
     */
    private void orientateOnRight(Adventurer adventurer) {
        switch (adventurer.getDirection()) {
            case N:
                adventurer.setDirection(Direction.E);
                break;
            case E:
                adventurer.setDirection(Direction.S);
                break;
            case O:
                adventurer.setDirection(Direction.N);
                break;
            case S:
                adventurer.setDirection(Direction.O);
                break;
            default:
                throw new EnumConstantNotPresentException(adventurer.getDirection().getDeclaringClass(),
                        String.format("Direction %s unknown.", adventurer.getDirection()));
        }
    }

}