package dp.behavior.state;

public class LecteurEnMarche implements Etat {
    @Override
    public void appuyerSurBoutonPlayPause(LecteurMp3 lecteurMp3) {
        lecteurMp3.setEtat(new LecteurEnPause());
        System.out.println("Lecteur MP3 en pause.");
    }
}
