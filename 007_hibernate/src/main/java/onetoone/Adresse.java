package onetoone;

import javax.persistence.*;

@Entity
@Table(name = "adresse")
public class Adresse {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rue")
    private String rue;

    @Column(name = "codePostal")
    private String codePostal;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

}
