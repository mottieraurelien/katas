package dp.behavior.visitor;

public class Musee extends Lieu implements Visitable {
    public Musee(String nom) {
        super(nom);
    }
    @Override
    public void accepter(Visiteur visiteur) {
        visiteur.visiter(this);
    }
}
