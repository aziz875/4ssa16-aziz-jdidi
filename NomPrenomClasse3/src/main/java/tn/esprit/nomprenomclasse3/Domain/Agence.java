package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    // Agence (1) --> (*) Employe
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private List<Employe> employes;

    // Agence (1) --> (*) Vehicule
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private List<Vehicule> vehicules;
}