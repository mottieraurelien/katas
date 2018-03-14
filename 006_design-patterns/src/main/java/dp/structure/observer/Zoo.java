package dp.structure.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Zoo extends Observable {
    private final List<Animal> animals = new ArrayList<>();
    public void addAnimal(Animal animal) {
        // Add the animal to the list of animals :
        this.animals.add(animal);
        // Indicate that the current observable object (zoo) has changed :
        this.setChanged();
        // Notify the list of registered observer :
        this.notifyObservers(animal);
    }
}
