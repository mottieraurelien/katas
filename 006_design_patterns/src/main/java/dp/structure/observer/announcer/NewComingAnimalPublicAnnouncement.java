package dp.structure.observer.announcer;

import dp.structure.observer.domain.Animal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NewComingAnimalPublicAnnouncement implements PropertyChangeListener {

    /**
     * Lorsqu'un nouvel animal intègre le zoo, il faut en faire la promotion en contactant la presse pour paraitre un
     * article dans le journal de la ville, il faut aussi contacter la télévision pour qu'elle vienne faire un petit
     * reportage et il faut aussi communiquer sur les réseaux sociaux.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent zooEventToAnnounce) {

        // Récupère le nom du nouvel arrivant au zoo :
        final Animal newAnimal = (Animal) zooEventToAnnounce.getNewValue();

        // L'annonce par la presse :
        System.out.println(newAnimal.getEmoji() + "Le zoo est fier d'annoncer la récente arrivée de " + newAnimal.getName() + ".");

        // L'annonce par la télévision :
        System.out.println(newAnimal.getEmoji() + newAnimal.getName() + " est enfin arrivée au zoo, venez lui rendre visite !");

        // L'annonce sur les réseaux sociaux :
        System.out.println(newAnimal.getEmoji() + newAnimal.getName() + " est trop mignon !");

    }

}