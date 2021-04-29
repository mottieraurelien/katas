package dp.structure.proxy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProxyInternet implements Internet {

    private static final Set<String> SITES_BANNIS = new HashSet<>(
            Arrays.asList("facebook.fr", "twitter.fr", "youtube.fr"));
    private Internet internet;

    public ProxyInternet() {
        this.internet = new RealInternet();
    }

    @Override
    public void acceder(String url) {
        if(SITES_BANNIS.contains(url.toLowerCase())){
            throw new SecurityException("Accès refusé.");
        }
        this.internet.acceder(url);
    }

}
