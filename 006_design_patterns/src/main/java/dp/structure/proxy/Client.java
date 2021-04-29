package dp.structure.proxy;

public class Client {
    public static void main (String[] args){
        Internet internet = new ProxyInternet();
        try {
            internet.acceder("lequipe.fr");
            internet.acceder("twitter.fr");
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
    }
}
