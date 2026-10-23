package tn.esprit.nomprenomclasse3.Domain;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"agence", "maintenances", "reservations", "equipements"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idVehicule;
    String immatriculation;
    String marque;
    String modele;

    @Enumerated(value = EnumType.STRING)
    CategorieVehicule categorie;

    BigDecimal tarifJournalier;

    @Enumerated(value = EnumType.STRING)
    StatutVehicule statut;

    @ManyToOne
    Agence agence;

    @OneToMany(mappedBy = "vehicule")
    List<Maintenance> maintenances;

    @OneToMany(mappedBy = "vehicule")
    List<Reservation> reservations;

    @ManyToMany
    List<Equipement> equipements;
}