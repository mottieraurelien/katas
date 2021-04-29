package dp.behavior.command;

public class Interrupteur {
    private Commande commande;
    public void setCommande(Commande commande){
        this.commande = commande;
    }
    public void appuyerBouton(){
        commande.executer();
    }
}
