package dp.behavior.state;

public class LecteurEnPause implements Etat {
    @Override
    public void appuyerSurBoutonPlayPause(LecteurMp3 lecteurMp3) {
        lecteurMp3.setEtat(new LecteurEnMarche());
        System.out.println("Lecteur MP3 en marche.");
    }
}
