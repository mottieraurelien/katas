package dp.structure.observer;

import java.util.Observer;

public class Client {
    public static void main(String[] args) {
        // Create the zoo to store animals :
        Zoo zoo = new Zoo();
        // Create the observer :
        Observer observer = new PrintNameAnimalAdded();
        // Register an observer to be notified when an animal is added :
        zoo.addObserver(observer);
        // Add an animal and notify the registered observer :
        zoo.addAnimal(new Animal("Tiger"));
        // Remove the observer to not be notified anymore when an animal is added :
        zoo.deleteObserver(observer);
        // Add another animal to check that the registered observer are not notified anymore :
        zoo.addAnimal(new Animal("Panda"));
    }
}
