package dp.structure.decorator;

public class Pizzeria {
    public static void main(String[] args) {
        Pizza quatreFromagesAvecAnchois = new Anchois(new QuatreFromages());
        System.out.println(String.format("Description : %s.", quatreFromagesAvecAnchois.getDescription()));
        System.out.println(String.format("Cout : %s€", quatreFromagesAvecAnchois.getCout()));
    }
}
