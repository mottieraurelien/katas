Scenario: An input file has provided and contains data for a treasures hunt by some adventurers.
We expect that the system read this input file, make the adventurers finding the treasures and write an output
file to display what are the entities (mountains, treasures spot, adventurers) on the treasures map and their
final positions.

Given an input file inputFile.txt that contains treasures hunt data
When the computer has processed the input file
Then the content of the generated file matches the expectedOutputFile.txt file content