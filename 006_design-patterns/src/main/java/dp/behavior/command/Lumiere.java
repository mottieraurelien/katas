package dp.behavior.command;

public class Lumiere {
    private boolean allumee;
    public void allumer() {
        allumee = true;
        System.out.println("Lumière allumée.");
    }
    public void eteindre() {
        allumee = false;
        System.out.println("Lumière éteinte.");
    }
}
