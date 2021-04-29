package dp.behavior.visitor;

public class Client {
    public static void main(String[] args) {
        // Notre visiteur :
        Visiteur visiteurAdulte = new Adulte();
        // Notre premier lieu qui peut être visité :
        Musee musee = new Musee("Le Louvre");
        // Visite du musée par un humain :
        musee.accepter(visiteurAdulte);
        // Notre second lieu qui peut être visité :
        Ecole ecole = new Ecole("l'école 42");
        // Visite du musée par un adulte :
        ecole.accepter(visiteurAdulte);
    }
}
