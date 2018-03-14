package commands;

import enumerations.Instruction;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * jUnit tests for {CommandProvider} class.
 */
public class CommandProviderTest {

    @Test
    public void getCommandImplementationWithoutAnyInstructio() {

        // [When]
        EntityCommand command = CommandProvider.getCommandImplementation(null);

        // [Then]
        assertNull(command);

    }

    @Test
    public void getCommandImplementationWhenMoveForwardInstructionNominalFlow() {

        // [When]
        EntityCommand command = CommandProvider.getCommandImplementation(Instruction.A);

        // [Then]
        assertTrue(command instanceof MoveCommand);

    }

    @Test
    public void getCommandImplementationWhenMoveBackwardInstructionNominalFlow() {

        // [When]
        EntityCommand command = CommandProvider.getCommandImplementation(Instruction.R);

        // [Then]
        assertTrue(command instanceof MoveCommand);

    }

    @Test
    public void getCommandImplementationWhenOrientateLeftInstructionNominalFlow() {

        // [When]
        EntityCommand command = CommandProvider.getCommandImplementation(Instruction.G);

        // [Then]
        assertTrue(command instanceof OrientateCommand);

    }

    @Test
    public void getCommandImplementationWhenOrientateRightInstructionNominalFlow() {

        // [When]
        EntityCommand command = CommandProvider.getCommandImplementation(Instruction.D);

        // [Then]
        assertTrue(command instanceof OrientateCommand);

    }

}
