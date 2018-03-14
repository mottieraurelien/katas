package manytomany;

import javax.persistence.*;
import java.util.List;

public class Livre {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinTable(name = "livre_auteur",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "auteur_id"))
    private List<Auteur> auteurs;

    // (...)

}
