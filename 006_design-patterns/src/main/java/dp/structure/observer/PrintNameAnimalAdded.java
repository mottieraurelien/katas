package dp.structure.observer;

import java.util.Observable;
import java.util.Observer;

public class PrintNameAnimalAdded implements Observer {
    @Override
    public void update(Observable zoo, Object animal) {
        // Print the name of the newly added animal :
        System.out.println("Added a new animal with name '" + ((Animal) animal).getName() + "'");
    }
}
