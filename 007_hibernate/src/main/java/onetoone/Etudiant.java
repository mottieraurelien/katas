package onetoone;

import onetomany.Ecole;

import javax.persistence.*;

@Entity
@Table(name = "etudiant")
public class Etudiant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "ecole_id")
    private Ecole ecole;

    // (...)

}