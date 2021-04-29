package dp.behavior.command;

public class AllumerLumiere implements Commande {
    private Lumiere lumiere;
    public AllumerLumiere(Lumiere lumiere){
        this.lumiere = lumiere;
    }
    @Override
    public void executer() {
        lumiere.allumer();
    }
}
