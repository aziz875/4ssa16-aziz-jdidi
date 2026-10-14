package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idVehicule;
    String immatriculation;
    String marque;
    String modele;

    @Enumerated(value = EnumType.STRING)
    CategorieVehicule categorie;

    BigDecimal tarifJournalier;

    @Enumerated(value = EnumType.STRING)
    StatutVehicule statut;

    // Vehicule (*) --> (1) Agence — Cascade: Aucune
    @ManyToOne
    @JoinColumn(name = "agence_id")
    Agence agence;

    // Vehicule (1) --> (*) Maintenance — Cascade: PERSIST
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.PERSIST)
    List<Maintenance> maintenances = new ArrayList<>();

    // Vehicule (*) <--> (*) Equipement
    @ManyToMany
    @JoinTable(
        name = "vehicule_equipement",
        joinColumns = @JoinColumn(name = "vehicule_id"),
        inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    Set<Equipement> equipements = new HashSet<>();
}
