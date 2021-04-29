package dp.behavior.visitor;

public class Adulte implements Visiteur {
    private static final String MESSAGE_PAYER = "L'adulte paie pour visiter %s.";
    private static final String MESSAGE_VISITER = "L'adulte visite %s.";
    @Override
    public void visiter(Musee musee) {
        System.out.println(String.format(MESSAGE_PAYER, musee.getNom()));
        System.out.println(String.format(MESSAGE_VISITER, musee.getNom()));
    }
    @Override
    public void visiter(Ecole ecole) {
        System.out.println(String.format(MESSAGE_VISITER, ecole.getNom()));
    }
}
