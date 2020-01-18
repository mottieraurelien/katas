package dp.structure.observer;

import dp.structure.observer.domain.Animal;
import dp.structure.observer.domain.Zoo;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {

        // Crée le zoo et initialise le système de notifications qui permettra d'en faire la promotion :
        final List<Animal> nursedAnimals = new ArrayList<>();
        final Zoo zoo = new Zoo(nursedAnimals);

        // Ajoute un nouveau venu :
        zoo.care(new Animal("Tigrou", "\uD83D\uDC2F"));

        // Ajoute encore un nouveau venu :
        zoo.care(new Animal("Po", "\uD83D\uDC3C"));

    }

}