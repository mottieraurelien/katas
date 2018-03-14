package commands;

import enumerations.Instruction;

public class CommandProvider {

    /**
     * Default constructor.
     */
    private CommandProvider() {
    }

    /**
     * Provides the right implementation of the EntityCommand according to the current instruction.
     *
     * @param instruction The instruction to take into consideration to provide the right
     *                    implementation.
     * @return The right implementation of the EntityCommand.
     */
    public static EntityCommand getCommandImplementation(Instruction instruction) {

        EntityCommand command = null;

        // According to the kind of the command :
        if (Instruction.getOrientateInstruction().contains(instruction)) {

            // Provides the orientate command :
            command = new OrientateCommand();

        } else if (Instruction.getMoveInstruction().contains(instruction)) {

            /// Provides the move command :
            command = new MoveCommand();

        }

        return command;

    }

}
