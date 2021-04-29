package dp.structure.facade;

public class SejourBreton implements Sejour {
    @Override
    public void organiser() {
        Transport transport = new TrainGrandeVitesse();
        transport.reserver();
        Hebergement hebergement = new Camping();
        hebergement.reserver();
    }
}
