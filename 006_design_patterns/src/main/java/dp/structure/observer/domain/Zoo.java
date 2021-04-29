package dp.structure.observer.domain;

import dp.structure.observer.announcer.NewComingAnimalOnboarding;
import dp.structure.observer.announcer.NewComingAnimalPublicAnnouncement;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class Zoo {

    private final List<Animal> animalsRegister;

    private final PropertyChangeSupport staffNotifier;
    private final PropertyChangeSupport publicNotifier;

    public Zoo(final List<Animal> animalsRegister) {

        // Initialise le registre des animaux du zoo :
        this.animalsRegister = animalsRegister;

        // Mise en place du système de prise en charge par le staff technique :
        this.staffNotifier = new PropertyChangeSupport(this);
        final PropertyChangeListener onboarding = new NewComingAnimalOnboarding();
        this.staffNotifier.addPropertyChangeListener(onboarding);

        // Mise en place du système d'annonce au public (journaux, TV, réseaux sociaux...) :
        this.publicNotifier = new PropertyChangeSupport(this);
        final PropertyChangeListener announcement = new NewComingAnimalPublicAnnouncement();
        this.publicNotifier.addPropertyChangeListener(announcement);

    }

    public void care(final Animal newAnimal) {

        // Ajoute le nouvel animal dans le registre des animaux du zoo :
        this.animalsRegister.add(newAnimal);

        // Diffuse l'information (sans savoir exactement comment cette information sera utilisée, car il s'agit
        // simplement d'une notification) :
        staffNotifier.firePropertyChange("Whatever", null, newAnimal);
        publicNotifier.firePropertyChange("Whatever", null, newAnimal);

    }

}