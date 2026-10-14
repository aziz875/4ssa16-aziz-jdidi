package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    private String nom;
    private String prenom;

    @Enumerated(EnumType.STRING)
    private RoleEmploye role;

    // Employe (*) --> (1) Agence — Cascade: Aucune
    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;
}