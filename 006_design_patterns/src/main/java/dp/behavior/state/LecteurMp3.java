package dp.behavior.state;

public class LecteurMp3 {
    private Etat etat;
    public LecteurMp3(Etat etat) {
        this.etat = etat;
    }
    public void play() {
        this.etat.appuyerSurBoutonPlayPause(this);
    }
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
