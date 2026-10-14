package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"agence", "maintenances", "equipements", "reservations"})
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

    // Vehicule (*) --> (1) Agence
    @ManyToOne
    @JoinColumn(name = "agence_id")
    Agence agence;

    // Vehicule (1) --> (*) Maintenance
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    List<Maintenance> maintenances;

    // Vehicule (*) <--> (*) Equipement
    @ManyToMany
    @JoinTable(
        name = "vehicule_equipement",
        joinColumns = @JoinColumn(name = "vehicule_id"),
        inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    List<Equipement> equipements;

    // Vehicule (1) --> (*) Reservation
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    List<Reservation> reservations;
}
