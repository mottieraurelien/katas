package onetomany;

import onetoone.Etudiant;

import javax.persistence.*;
import java.util.List;

public class Ecole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "ecole", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etudiant> etudiants;

    // (...)

}
