package dp.structure.facade;

public class Camping implements Hebergement {
    @Override
    public void reserver() {
        System.out.println("Réservation d'un emplacement pour la tente.");
    }
}
