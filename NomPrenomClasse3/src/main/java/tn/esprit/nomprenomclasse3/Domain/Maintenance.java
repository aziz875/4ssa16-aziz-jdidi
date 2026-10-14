package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "vehicule")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idMaintenance;
    LocalDate dateDebut;
    LocalDate dateFin;
    String description;

    // Maintenance (*) --> (1) Vehicule
    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    Vehicule vehicule;
}
