package commands;

import enumerations.Direction;
import enumerations.EntityType;
import enumerations.Instruction;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import models.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * jUnit tests for {MoveCommand} class.
 */
@RunWith(JUnitParamsRunner.class)
public class MoveCommandTest {

    private static final int ABSCISSA = 1;
    private static final int ORDINATE = 1;
    private static final Coordinates COORDINATES = new Coordinates(ABSCISSA, ORDINATE);

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private TreasuresMap treasuresMapMock;
    @Mock
    private TreasuresSpot treasuresSpotMock;
    @Mock
    private Map<Coordinates, List<Entity>> availableCoordinatesMock;
    @Mock
    private Adventurer adventurerMock;
    @Mock
    private Entity entityMock;

    @InjectMocks
    private MoveCommand moveCommand;

    public static Collection<Object[]> scenariosWhenAdventurerOutOfTheTreasuresMap() {
        return commonData();
    }

    public static Collection<Object[]> scenariosWhenAdventurerOverMountain() {
        return commonData();
    }

    public static Collection<Object[]> scenariosWhenAdventurerOverExistingAdventurer() {
        return commonData();
    }

    public static Collection<Object[]> scenariosWhenAdventurerOverPlains() {
        return commonData();
    }

    public static Collection<Object[]> scenariosWhenAdventurerOverTreasuresSpot() {
        return commonData();
    }

    private static Collection<Object[]> commonData() {
        return Arrays.asList(new Object[]{Direction.E, COORDINATES, new Coordinates(2, 1)},
                new Object[]{Direction.O, COORDINATES, new Coordinates(0, 1)},
                new Object[]{Direction.S, COORDINATES, new Coordinates(1, 2)},
                new Object[]{Direction.N, COORDINATES, new Coordinates(1, 0)});
    }

    @Test
    public void executeWithUnhandledInstructionThrowsUnsupportedOperationException() {

        // [Given] Not needed.

        // [When / Then]
        assertThatThrownBy(() -> moveCommand.execute(treasuresMapMock, adventurerMock, Instruction.R))
                .isInstanceOf(UnsupportedOperationException.class);

    }

    @Test
    @Parameters(method = "scenariosWhenAdventurerOutOfTheTreasuresMap")
    public void executeWithAdventurerOutOfTheTreasuresMap(final Direction currentAdventurerDirection,
                                                          final Coordinates expectedFinalAdventurerCoordinatesIfCantMove,
                                                          final Coordinates expectedFinalAdventurerCoordinatesIfCanMove) {

        // [Given]
        Mockito.when(adventurerMock.getCoordinates()).thenReturn(COORDINATES);
        Mockito.when(treasuresMapMock.getAvailableCoordinates()).thenReturn(availableCoordinatesMock);
        Mockito.when(adventurerMock.getDirection()).thenReturn(currentAdventurerDirection);

        // [When]
        moveCommand.execute(treasuresMapMock, adventurerMock, Instruction.A);

        // [Then] we check that the adventurer was not be able to move.
        Mockito.verify(adventurerMock, Mockito.never()).setCoordinates(Mockito.any());

    }

    @Test
    @Parameters(method = "scenariosWhenAdventurerOverMountain")
    public void executeWithAdventurerOverMountain(final Direction currentAdventurerDirection,
                                                  final Coordinates expectedFinalAdventurerCoordinatesIfCantMove,
                                                  final Coordinates expectedFinalAdventurerCoordinatesIfCanMove) {

        // [Given]
        Mockito.when(adventurerMock.getCoordinates()).thenReturn(COORDINATES);
        Mockito.when(treasuresMapMock.getAvailableCoordinates()).thenReturn(availableCoordinatesMock);
        Mockito.when(adventurerMock.getDirection()).thenReturn(currentAdventurerDirection);
        Mockito.when(availableCoordinatesMock.containsKey(expectedFinalAdventurerCoordinatesIfCanMove))
                .thenReturn(true);
        // [Given] Specific for this test.
        Mockito.when(entityMock.getEntityType()).thenReturn(EntityType.M);
        Mockito.when(availableCoordinatesMock.get(expectedFinalAdventurerCoordinatesIfCanMove))
                .thenReturn(new ArrayList<>(Arrays.asList(entityMock, null)));

        // [When]
        moveCommand.execute(treasuresMapMock, adventurerMock, Instruction.A);

        // [Then]
        Mockito.verify(adventurerMock, Mockito.never()).setCoordinates(Mockito.any());

    }

    @Test
    @Parameters(method = "scenariosWhenAdventurerOverExistingAdventurer")
    public void executeWithAdventurerOverExistingAdventurer(
            final Direction currentAdventurerDirection,
            final Coordinates expectedFinalAdventurerCoordinatesIfCantMove,
            final Coordinates expectedFinalAdventurerCoordinatesIfCanMove) {

        // [Given]
        Mockito.when(adventurerMock.getCoordinates()).thenReturn(COORDINATES);
        Mockito.when(treasuresMapMock.getAvailableCoordinates()).thenReturn(availableCoordinatesMock);
        Mockito.when(adventurerMock.getDirection()).thenReturn(currentAdventurerDirection);
        Mockito.when(availableCoordinatesMock
                .containsKey(Mockito.eq(expectedFinalAdventurerCoordinatesIfCanMove))).thenReturn(true);
        // [Given] Specific for this test.
        Mockito.when(entityMock.getEntityType()).thenReturn(EntityType.A);
        Mockito.when(availableCoordinatesMock.get(expectedFinalAdventurerCoordinatesIfCanMove))
                .thenReturn(new ArrayList<>(Arrays.asList(entityMock)));

        // [When]
        moveCommand.execute(treasuresMapMock, adventurerMock, Instruction.A);

        // [Then]
        Mockito.verify(adventurerMock, Mockito.never()).setCoordinates(Mockito.any());

    }

    @Test
    @Parameters(method = "scenariosWhenAdventurerOverPlains")
    public void executeWithAdventurerOverPlains(final Direction currentAdventurerDirection,
                                                final Coordinates expectedFinalAdventurerCoordinatesIfCantMove,
                                                final Coordinates expectedFinalAdventurerCoordinatesIfCanMove) {

        // [Given]
        Mockito.when(adventurerMock.getCoordinates()).thenReturn(COORDINATES);
        Mockito.when(treasuresMapMock.getAvailableCoordinates()).thenReturn(availableCoordinatesMock);
        Mockito.when(adventurerMock.getDirection()).thenReturn(currentAdventurerDirection);
        Mockito.when(availableCoordinatesMock
                .containsKey(Mockito.eq(expectedFinalAdventurerCoordinatesIfCanMove))).thenReturn(true);
        // [Given] Specific for this test.
        Mockito.when(availableCoordinatesMock.get(expectedFinalAdventurerCoordinatesIfCantMove))
                .thenReturn(new ArrayList<>(Arrays.asList(adventurerMock)));
        Mockito.when(availableCoordinatesMock.get(expectedFinalAdventurerCoordinatesIfCanMove))
                .thenReturn(new ArrayList<>());

        // [When]
        moveCommand.execute(treasuresMapMock, adventurerMock, Instruction.A);

        // [Then]
        Mockito.verify(adventurerMock).setCoordinates(expectedFinalAdventurerCoordinatesIfCanMove);

    }

    @Test
    @Parameters(method = "scenariosWhenAdventurerOverTreasuresSpot")
    public void executeWithAdventurerOverTreasuresSpot(final Direction currentAdventurerDirection,
                                                       final Coordinates expectedFinalAdventurerCoordinatesIfCantMove,
                                                       final Coordinates expectedFinalAdventurerCoordinatesIfCanMove) {

        // [Given]
        Mockito.when(adventurerMock.getCoordinates()).thenReturn(COORDINATES);
        Mockito.when(treasuresMapMock.getAvailableCoordinates()).thenReturn(availableCoordinatesMock);
        Mockito.when(adventurerMock.getDirection()).thenReturn(currentAdventurerDirection);
        Mockito.when(availableCoordinatesMock
                .containsKey(Mockito.eq(expectedFinalAdventurerCoordinatesIfCanMove))).thenReturn(true);
        // [Given] Specific for this test.
        Mockito.when(availableCoordinatesMock.get(expectedFinalAdventurerCoordinatesIfCantMove))
                .thenReturn(new ArrayList<>(Arrays.asList(adventurerMock)));
        Mockito.when(treasuresSpotMock.getCoordinates())
                .thenReturn(expectedFinalAdventurerCoordinatesIfCanMove);
        Mockito.when(treasuresSpotMock.getEntityType()).thenReturn(EntityType.T);
        Mockito.when(availableCoordinatesMock.get(expectedFinalAdventurerCoordinatesIfCanMove))
                .thenReturn(new ArrayList<>(Arrays.asList(treasuresSpotMock)));

        // [When]
        moveCommand.execute(treasuresMapMock, adventurerMock, Instruction.A);

        // [Then]
        Mockito.verify(treasuresSpotMock).oneTreasureHasBeenFound();
        Mockito.verify(adventurerMock).setCoordinates(expectedFinalAdventurerCoordinatesIfCanMove);

    }

}
