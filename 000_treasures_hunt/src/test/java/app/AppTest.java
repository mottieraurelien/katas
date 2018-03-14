package app;

import models.TreasuresMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * jUnit tests for {app.App} class.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Files.class, App.class})
public class AppTest {

    @Mock
    private TreasuresMap treasuresMapMock;
    @Mock
    private File inputFileMock;
    @Mock
    private Path pathInputFileMock;

    @InjectMocks
    private App app;

    /**
     * Before each test.
     */
    @Before
    public void before() {

        // To be able to mock static method call on Files :
        PowerMockito.mockStatic(Files.class);

        // Common [Given]
        Mockito.when(inputFileMock.toPath()).thenReturn(pathInputFileMock);

    }

    @Test
    public void simulateAdventurersMovesWithoutAnyTreasuresMapDefinition() throws IOException {

        // [Given]
        List<String> fileContentWithoutTreasuresMapDefinition = prepareInputFileContentForNominalFlow();
        fileContentWithoutTreasuresMapDefinition.remove(0);
        Mockito.when(Files.readAllLines(pathInputFileMock))
                .thenReturn(fileContentWithoutTreasuresMapDefinition);

        // [When]
        File actualOutputFile = app.simulateAdventurersMoves(inputFileMock);

        // [Then]
        assertNull(actualOutputFile);

    }

    @Test
    public void simulateAdventurersMovesNominalFlow() throws Exception {

        // [Given]
        PowerMockito.whenNew(TreasuresMap.class).withNoArguments().thenReturn(treasuresMapMock);
        List<String> fileContent = this.prepareInputFileContentForNominalFlow();
        Mockito.when(Files.readAllLines(pathInputFileMock)).thenReturn(fileContent);

        // [When]
        File actualOutputFile = app.simulateAdventurersMoves(inputFileMock);

        // [Then]
        assertNotNull(actualOutputFile);
        Mockito.verify(treasuresMapMock).setDimensions(Mockito.any());
        Mockito.verify(treasuresMapMock).setMountains(Mockito.any());
        Mockito.verify(treasuresMapMock).setTreasuresSpots(Mockito.any());
        Mockito.verify(treasuresMapMock).setAdventurers(Mockito.any());
        Mockito.verify(treasuresMapMock).makeTheAdventurersFindingTheTreasures();

    }

    /**
     * Prepares the data extracted from an input file.
     *
     * @return The data extracted from an input file.
     */
    private List<String> prepareInputFileContentForNominalFlow() {

        List<String> fileContent = new ArrayList<>();

        // File content :
        fileContent.add("C - 3 - 4");
        fileContent.add("M - 1 - 0");
        fileContent.add("M - 2 - 1");
        fileContent.add("T - 0 - 3 - 2");
        fileContent.add("T - 1 - 3 - 3");
        fileContent.add("A - Lara - 1 - 1 - S - AADADAGGA");

        return fileContent;

    }


}
