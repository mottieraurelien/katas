package jbehave.steps;

import app.App;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class TreasuresMapSteps {

    private static final App APP = new App();

    private File inputProvidedFile;
    private File actualOutputFile;

    /**
     * "Given an input file inputFile.txt that contains treasures hunt data"
     *
     * @param fileName The input file name.
     */
    @SuppressWarnings("unused")
    @Given("an input file $filename that contains treasures hunt data")
    public void givenAnInputFileThatContainsTreasuresHuntData(@Named("fileName") String fileName) {

        // Retrieves the input file from the classpath :
        ClassLoader classLoader = getClass().getClassLoader();
        inputProvidedFile = new File(classLoader.getResource(fileName).getFile());

    }

    /**
     * "When the computer has processed the input file".
     *
     * @throws IOException If an error occurred when processing the input file.
     */
    @SuppressWarnings("unused")
    @When("the computer has processed the input file")
    public void whenTheComputerHasProcessedTheInputFile() throws IOException {

        // Calls the program :
        actualOutputFile = APP.simulateAdventurersMoves(inputProvidedFile);

    }

    /**
     * "Then the content of the generated file matches the expectedOutput.txt file content".
     *
     * @param filenameOfExpectedFile Filename of the expected file.
     * @throws IOException If an error occured when processing the output files (actual VS expected).
     */
    @SuppressWarnings("unused")
    @Then("the content of the generated file matches the $filenameOfExpectedFile file content")
    public void thenActualOutputFileContentMatchesExpectedOutputFileContent(
            @Named("filenameOfExpectedFile") String filenameOfExpectedFile) throws IOException {

        // Retrieves the expect output file from the classpath :
        ClassLoader classLoader = getClass().getClassLoader();
        File expectedOutputFile = new File(classLoader.getResource(filenameOfExpectedFile).getFile());

        // Retrieves the content of the expected output file :
        List<String> expectedFileContent = Files.readAllLines(expectedOutputFile.toPath());

        // Retrieves the content of the actual output file :
        List<String> actualFileContent = Files.readAllLines(actualOutputFile.toPath());

        // Compares both content :
        assertEquals(expectedFileContent.size(), actualFileContent.size());
        assertThat(actualFileContent).containsAll(expectedFileContent);

    }

}
