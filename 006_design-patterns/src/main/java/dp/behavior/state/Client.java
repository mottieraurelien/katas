package dp.behavior.state;

public class Client {
    public static void main(String[] args) {
        // Crée un objet qui va subir des changements d'états en lui affectant un état initial:
        LecteurMp3 lecteurMp3 = new LecteurMp3(new LecteurEnPause());
        // Nous jouons la musique :
        lecteurMp3.play();
        // Nous le mettons en pause :
        lecteurMp3.play();
        // Nous rejouons la musique :
        lecteurMp3.play();
    }
}
