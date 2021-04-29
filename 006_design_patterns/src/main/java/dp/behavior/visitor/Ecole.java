package dp.behavior.visitor;

public class Ecole extends Lieu implements Visitable {
    public Ecole(String nom) {
        super(nom);
    }
    @Override
    public void accepter(Visiteur visiteur) {
        visiteur.visiter(this);
    }
}
