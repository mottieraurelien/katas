package dp.behavior.command;

public class EteindreLumiere implements Commande {
    private Lumiere lumiere;
    public EteindreLumiere(Lumiere lumiere){
        this.lumiere = lumiere;
    }
    @Override
    public void executer() {
        lumiere.eteindre();
    }
}
