package commands;

import enumerations.Direction;
import enumerations.Instruction;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import models.Adventurer;
import models.Coordinates;
import models.TreasuresMap;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * jUnit tests for {OrientateCommand} class.
 */
@RunWith(JUnitParamsRunner.class)
public class OrientateCommandTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private TreasuresMap treasuresMapMock;
    @Mock
    private Coordinates coordinatesMock;

    @InjectMocks
    private OrientateCommand orientateCommand;

    /**
     * Defines scenarios to test all the nominal flows of the execute method.
     *
     * @return The defined scenarios.
     */
    public static Collection<Object[]> executeMethodParams() {
        return Arrays.asList(new Object[]{Direction.E, Instruction.G, Direction.N},
                new Object[]{Direction.E, Instruction.D, Direction.S},
                new Object[]{Direction.O, Instruction.G, Direction.S},
                new Object[]{Direction.O, Instruction.D, Direction.N},
                new Object[]{Direction.S, Instruction.G, Direction.E},
                new Object[]{Direction.S, Instruction.D, Direction.O},
                new Object[]{Direction.N, Instruction.G, Direction.O},
                new Object[]{Direction.N, Instruction.D, Direction.E},
                new Object[]{Direction.N, null, Direction.N});
    }

    @Test
    @Parameters(method = "executeMethodParams")
    public void orientateNominalFlows(final Direction currentAdventurerDirection,
                                      final Instruction orientateInstruction, final Direction expectedAdventurerDirection) {

        // [Given]
        Adventurer adventurer =
                new Adventurer(coordinatesMock, "WHATEVER", currentAdventurerDirection, "WHATEVER");

        // [When]
        orientateCommand.execute(treasuresMapMock, adventurer, orientateInstruction);

        // [Then]
        assertEquals(expectedAdventurerDirection, adventurer.getDirection());

    }

}
