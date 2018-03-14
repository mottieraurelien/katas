package dp.structure.proxy;

public class RealInternet implements Internet {
    @Override
    public void acceder(String url) {
        System.out.println("Accès au site " + url);
    }
}
