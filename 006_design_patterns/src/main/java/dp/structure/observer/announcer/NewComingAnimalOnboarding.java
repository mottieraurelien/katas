package dp.structure.observer.announcer;

import dp.structure.observer.domain.Animal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NewComingAnimalOnboarding implements PropertyChangeListener {

    /**
     * Lorsqu'un nouvel animal intègre le zoo, il faut que le staff le prenne en charge.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent newAnimalToCare) {

        // Récupère le nom du nouvel arrivant au zoo :
        final Animal newAnimal = (Animal) newAnimalToCare.getNewValue();

        // Lui passe sa visite médicale à son arrivée :
        System.out.println(newAnimal.getEmoji() + newAnimal.getName() + " a passé sa visite médicale et il va très bien, le transport s'est bien passé.");

        // Installe l'animal dans son box :
        System.out.println(newAnimal.getEmoji() + newAnimal.getName() + " a été installé dans son box avec de la nourriture et de l'eau.");

        // Ajout de l'animal au planning du staff :
        System.out.println(newAnimal.getEmoji() + newAnimal.getName() + " a été ajouté au planning.");

    }

}