package dp.behavior.command;

public class Client {
    public static void main(String[] args) {
        // Notre initiateur ou invoker :
        Interrupteur interrupteur = new Interrupteur();
        // Notre receiver :
        Lumiere lumiere = new Lumiere();
        // Les différentes commandes que l'initiateur peut déclencher :
        Commande allumerLumiere = new AllumerLumiere(lumiere);
        Commande eteindreLumiere = new EteindreLumiere(lumiere);
        // Allumage de la lumière en actionnant l'interrupteur une première fois :
        interrupteur.setCommande(allumerLumiere);
        interrupteur.appuyerBouton();
        // Extinction de la lumière en actionnant l'interrupteur une seconde fois :
        interrupteur.setCommande(eteindreLumiere);
        interrupteur.appuyerBouton();
    }
}
