package dp.structure.facade;

public class TrainGrandeVitesse implements Transport {
    @Override
    public void reserver() {
        System.out.println("Réservation de billets auprès de la SNCF pour un trajet en TGV.");
    }
}
