package app;

import enumerations.Direction;
import enumerations.EntityType;
import models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Treasures map app.
 */
public class App {

    private static final String SEPARATOR = " - ";
    private static final String LINE = "%s\n";

    public App() {

    }

    /**
     * Simulate the treasures hunt in making adventurers finding them.
     *
     * @param inputFile The input file to process.
     * @throws IOException If an error occurs when reading the input file.
     */
    public File simulateAdventurersMoves(File inputFile) throws IOException {

        File ouputFile = null;

        // Reads the input file and parse contained data :
        List<String> inputFileData = Files.readAllLines(inputFile.toPath());

        // Defines the treasures map :
        TreasuresMap treasuresMap = this.defineTreasuresMap(inputFileData);

        // If the treasures map has been setup :
        if (treasuresMap != null) {

            // Puts the mountains on the treasures map :
            treasuresMap.setMountains(this.defineMountains(inputFileData));

            // Puts the treasures spots on the treasures map :
            treasuresMap.setTreasuresSpots(this.defineTreasuresSpots(inputFileData));

            // Puts the adventurers on the treasures map :
            treasuresMap.setAdventurers(this.defineAdventurers(inputFileData));

            // Makes the adventurers finding the treasures :
            treasuresMap.makeTheAdventurersFindingTheTreasures();

            // Fills the output file :
            ouputFile = generateOutputFile(treasuresMap);

        }

        // Returns the content of the treasures map in an output file (in the same folder of the input
        // file) :
        return ouputFile;

    }

    private TreasuresMap defineTreasuresMap(List<String> inputFileData) {

        TreasuresMap treasuresMap = null;

        // Tries to find the map definition :
        Optional<String> mapDefinition = inputFileData.stream()
                .filter(inputFileLine -> EntityType.C == EntityType.valueOf(inputFileLine.substring(0, 1)))
                .findFirst();

        // If a treasures map's definition has been found :
        if (mapDefinition.isPresent()) {

            // Identifies treasures map's information such as coordinates :
            List<String> mapCoordinates = Pattern.compile(SEPARATOR).splitAsStream(mapDefinition.get())
                    .collect(Collectors.toList());

            // Retrieves the max coordinates of the treasures map :
            Coordinates maxCoordinates = new Coordinates(Integer.parseInt(mapCoordinates.get(1)),
                    Integer.parseInt(mapCoordinates.get(2)));

            // Initializes the treasures map :
            treasuresMap = new TreasuresMap();

            // Sets the dimensions of the treasures map :
            treasuresMap.setDimensions(new Dimensions(maxCoordinates));

        }

        return treasuresMap;

    }

    private List<Mountain> defineMountains(List<String> inputFileData) {

        List<Mountain> mountains = new ArrayList<>();

        // Tries to find all moutains definitions :
        List<String> mountainsDefinitions = inputFileData.stream()
                .filter(inputFileLine -> EntityType.M == EntityType.valueOf(inputFileLine.substring(0, 1)))
                .collect(Collectors.toList());

        // Looks over the found mountains :
        mountainsDefinitions.forEach(mountainDefinition -> {

            // Identifies mountain's information such as coordinates :
            List<String> mountainCoordinates =
                    Pattern.compile(SEPARATOR).splitAsStream(mountainDefinition).collect(Collectors.toList());

            // Retrieves the coordinates of the mountain :
            Coordinates moutainCoordinates = new Coordinates(Integer.parseInt(mountainCoordinates.get(1)),
                    Integer.parseInt(mountainCoordinates.get(2)));

            // Adds the identified mountain to the mountains list :
            mountains.add(new Mountain(moutainCoordinates));

        });

        return mountains;

    }

    private List<TreasuresSpot> defineTreasuresSpots(List<String> inputFileData) {

        List<TreasuresSpot> treasuresSpots = new ArrayList<>();

        // Tries to find all treasures spots definitions :
        List<String> treasuresSpotsDefinitions = inputFileData.stream()
                .filter(inputFileLine -> EntityType.T == EntityType.valueOf(inputFileLine.substring(0, 1)))
                .collect(Collectors.toList());

        // Looks over the found treasures spots :
        treasuresSpotsDefinitions.forEach(treasuresSpotDefinitions -> {

            // Identifies treasures spot's information such as coordinates and number of treasures :
            List<String> treasuresSpotCoordinatesAndNbTreasures = Pattern.compile(SEPARATOR)
                    .splitAsStream(treasuresSpotDefinitions).collect(Collectors.toList());

            // Retrieves the coordinates of the treasures spot :
            Coordinates treasuresSpotCoordinates =
                    new Coordinates(Integer.parseInt(treasuresSpotCoordinatesAndNbTreasures.get(1)),
                            Integer.parseInt(treasuresSpotCoordinatesAndNbTreasures.get(2)));

            // Retrieves the number of treasures in this spot :
            int nbTreasures = Integer.parseInt(treasuresSpotCoordinatesAndNbTreasures.get(3));

            // Adds the identified treasures spot to the treasures spots list :
            treasuresSpots.add(new TreasuresSpot(treasuresSpotCoordinates, nbTreasures));

        });

        return treasuresSpots;

    }

    private List<Adventurer> defineAdventurers(List<String> inputFileData) {

        List<Adventurer> adventurers = new ArrayList<>();

        // Tries to find all adventurers definitions :
        List<String> adventurersDefinitions = inputFileData.stream()
                .filter(inputFileLine -> EntityType.A == EntityType.valueOf(inputFileLine.substring(0, 1)))
                .collect(Collectors.toList());

        // Looks over the found adventurers :
        adventurersDefinitions.forEach(adventurerDefinitions -> {

            // Identifies adventurer's information such as firstname, coordinates, direction and
            // instructions :
            List<String> adventurerData = Pattern.compile(SEPARATOR).splitAsStream(adventurerDefinitions)
                    .collect(Collectors.toList());

            // Retrieves the firstname of the adventurer :
            String firstname = adventurerData.get(1);

            // Retrieves the initial position of the adventurer :
            Coordinates adventurerCoordinates = new Coordinates(Integer.parseInt(adventurerData.get(2)),
                    Integer.parseInt(adventurerData.get(3)));

            // Retrieves the direction of the adventurer :
            Direction direction = Direction.valueOf(adventurerData.get(4));

            // Retrieves the instructions of the adventurer :
            String instructions = adventurerData.get(5);

            // Adds the identified adventurer to the adventurers list :
            adventurers.add(new Adventurer(adventurerCoordinates, firstname, direction, instructions));

        });

        return adventurers;

    }

    private File generateOutputFile(TreasuresMap treasuresMap) throws IOException {

        // Creates a temporary file :
        File ouputFile = File.createTempFile("actualOutputFile", ".txt");

        // Fills the output file with the results :
        try (FileWriter writer = new FileWriter(ouputFile)) {

            // Fills the content with the treasures map dimensions :
            writer.write(String.format(LINE, treasuresMap.toString()));

            // Fills the content with the mountains coordinates :
            for (Mountain mountain : treasuresMap.getMountains()) {
                writer.write(String.format(LINE, mountain.toString()));
            }

            // Fills the content with the remaining treasures spots :
            for (TreasuresSpot treasuresSpot : treasuresMap.getTreasuresSpots()) {
                writer.write(String.format(LINE, treasuresSpot.toString()));
            }

            // Fills the content with the adventurers :
            for (Adventurer adventurer : treasuresMap.getAdventurers()) {
                writer.write(String.format(LINE, adventurer.toString()));
            }

        }

        return ouputFile;

    }

}
