package models;

import commands.CommandProvider;
import commands.EntityCommand;
import enumerations.Direction;
import enumerations.Instruction;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

/**
 * jUnit tests for {TreasuresMap} class.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommandProvider.class)
public class TreasuresMapTest {

    private static final int ABSCISSA = 1;
    private static final int ORDINATE = 1;
    private static final String UNHANDLED_INSTRUCTION = "X";
    private static final String HANDLED_INSTRUCTIONS = "GAD";

    @Mock
    private Coordinates coordinatesMock;
    @Mock
    private Coordinates otherCoordinatesMock;
    @Mock
    private Dimensions dimensionsMock;
    @Mock
    private Mountain mountainMock;
    @Mock
    private Mountain otherMountainMock;
    @Mock
    private Adventurer adventurerMock;
    @Mock
    private Adventurer otherAdventurerMock;
    @Mock
    private TreasuresSpot treasuresSpotMock;
    @Mock
    private TreasuresSpot otherTreasuresSpotMock;
    @Mock
    private Map<Coordinates, List<Entity>> availableCoordinatesMock;
    @Mock
    private EntityCommand entityCommandMock;

    @Test
    public void constructorNominalFlow() {

        // [Given] Not needed.

        // [When]
        TreasuresMap treasuresMap = new TreasuresMap();

        // [Then]
        assertNotNull(treasuresMap.getAvailableCoordinates());

    }

    @Test
    public void getterSetterDimensionsNominalFlow() {

        // [Given]
        Mockito.when(coordinatesMock.getAbscissa()).thenReturn(ABSCISSA);
        Mockito.when(coordinatesMock.getOrdinate()).thenReturn(ORDINATE);
        Mockito.when(dimensionsMock.getMaxCoordinates()).thenReturn(coordinatesMock);
        TreasuresMap treasuresMap = new TreasuresMap();

        // [When]
        treasuresMap.setDimensions(dimensionsMock);

        // [Then]
        assertEquals(dimensionsMock, treasuresMap.getDimensions());
        // 4 available coordinates because 2 possible absissas (0 and 1) and 2 possible ordinates (0
        // and 1 too)0 These 4 expected available coordinates are {0-0}, {0-1}, {1-0}, {1-1}.
        assertEquals(4, treasuresMap.getAvailableCoordinates().size());
        // Available coordinates are initialized with only plains.
        assertTrue(treasuresMap.getAvailableCoordinates().values().stream().allMatch(List::isEmpty));

    }

    @Test
    public void getterSetterMountainsNominalFlow() {

        // [Given]
        Mockito.when(mountainMock.getCoordinates()).thenReturn(coordinatesMock);
        Mockito.when(otherMountainMock.getCoordinates()).thenReturn(otherCoordinatesMock);
        List<Mountain> mountains = Arrays.asList(mountainMock, otherMountainMock);
        TreasuresMap treasuresMap = new TreasuresMap();

        // [When]
        treasuresMap.setMountains(mountains);

        // [Then]
        assertEquals(mountains, treasuresMap.getMountains());
        Assertions.assertThat(treasuresMap.getAvailableCoordinates().get(coordinatesMock))
                .containsExactly(mountainMock);
        Assertions.assertThat(treasuresMap.getAvailableCoordinates().get(otherCoordinatesMock))
                .containsExactly(otherMountainMock);

    }

    @Test
    public void getterSetterTreasuresSpotsNominalFlow() {

        // [Given]
        Mockito.when(treasuresSpotMock.getCoordinates()).thenReturn(coordinatesMock);
        Mockito.when(otherTreasuresSpotMock.getCoordinates()).thenReturn(otherCoordinatesMock);
        List<TreasuresSpot> treasuresSpots = Arrays.asList(treasuresSpotMock, otherTreasuresSpotMock);
        TreasuresMap treasuresMap = new TreasuresMap();

        // [When]
        treasuresMap.setTreasuresSpots(treasuresSpots);

        // [Then]
        assertEquals(treasuresSpots, treasuresMap.getTreasuresSpots());
        Assertions.assertThat(treasuresMap.getAvailableCoordinates().get(coordinatesMock))
                .containsExactly(treasuresSpotMock);
        Assertions.assertThat(treasuresMap.getAvailableCoordinates().get(otherCoordinatesMock))
                .containsExactly(otherTreasuresSpotMock);

    }

    @Test
    public void getterSetterAdventurersNominalFlow() {

        // [Given]
        Mockito.when(adventurerMock.getCoordinates()).thenReturn(coordinatesMock);
        Mockito.when(otherAdventurerMock.getCoordinates()).thenReturn(otherCoordinatesMock);
        List<Adventurer> adventurers = Arrays.asList(adventurerMock, otherAdventurerMock);
        TreasuresMap treasuresMap = new TreasuresMap();

        // [When]
        treasuresMap.setAdventurers(adventurers);

        // [Then]
        assertEquals(adventurers, treasuresMap.getAdventurers());
        Assertions.assertThat(treasuresMap.getAvailableCoordinates().get(coordinatesMock))
                .containsExactly(adventurerMock);
        Assertions.assertThat(treasuresMap.getAvailableCoordinates().get(otherCoordinatesMock))
                .containsExactly(otherAdventurerMock);

    }

    @Test
    public void getterSetterAvailableCoordinatesNominalFlow() {

        // [Given]
        TreasuresMap treasuresMap = new TreasuresMap();

        // [When]
        treasuresMap.setAvailableCoordinates(availableCoordinatesMock);

        // [Then]
        assertEquals(availableCoordinatesMock, treasuresMap.getAvailableCoordinates());

    }

    @Test
    public void makeTheAdventurersFindingTheTreasuresWithUnhandledInstruction() {

        // [Given]
        Mockito.when(adventurerMock.getInstructions()).thenReturn(UNHANDLED_INSTRUCTION);
        Mockito.when(adventurerMock.getCoordinates()).thenReturn(coordinatesMock);
        List<Adventurer> adventurers = Collections.singletonList(adventurerMock);
        TreasuresMap treasuresMap = new TreasuresMap();
        treasuresMap.setAdventurers(adventurers);

        // [When / Then]
        assertThatThrownBy(treasuresMap::makeTheAdventurersFindingTheTreasures)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void makeTheAdventurersFindingTheTreasuresNominalFlow() {

        // To be able to mock static method call on CommandProvider :
        PowerMockito.mockStatic(CommandProvider.class);

        // [Given]
        Mockito.when(CommandProvider.getCommandImplementation(Mockito.any()))
                .thenReturn(entityCommandMock);
        Mockito.when(adventurerMock.getDirection()).thenReturn(Direction.N);
        Mockito.when(otherAdventurerMock.getDirection()).thenReturn(Direction.E);
        Mockito.when(adventurerMock.getInstructions()).thenReturn(HANDLED_INSTRUCTIONS);
        Mockito.when(otherAdventurerMock.getInstructions()).thenReturn(HANDLED_INSTRUCTIONS);
        Mockito.when(adventurerMock.getCoordinates()).thenReturn(coordinatesMock);
        Mockito.when(otherAdventurerMock.getCoordinates()).thenReturn(otherCoordinatesMock);
        List<Adventurer> adventurers = Arrays.asList(adventurerMock, otherAdventurerMock);
        TreasuresMap treasuresMap = new TreasuresMap();
        treasuresMap.setAdventurers(adventurers);

        // [When]
        treasuresMap.makeTheAdventurersFindingTheTreasures();

        // [Then]
        Mockito.verify(entityCommandMock).execute(treasuresMap, adventurerMock, Instruction.G);
        Mockito.verify(entityCommandMock).execute(treasuresMap, adventurerMock, Instruction.A);
        Mockito.verify(entityCommandMock).execute(treasuresMap, adventurerMock, Instruction.D);
        Mockito.verify(entityCommandMock).execute(treasuresMap, otherAdventurerMock, Instruction.G);
        Mockito.verify(entityCommandMock).execute(treasuresMap, otherAdventurerMock, Instruction.A);
        Mockito.verify(entityCommandMock).execute(treasuresMap, otherAdventurerMock, Instruction.D);

    }


}
