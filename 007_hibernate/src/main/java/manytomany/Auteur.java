package manytomany;

import javax.persistence.*;
import java.util.List;

public class Auteur {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "auteurs")
    private List<Livre> livres;

    // (...)

}
