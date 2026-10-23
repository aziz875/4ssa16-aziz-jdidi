package tn.esprit.nomprenomclasse3.Domain;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idMaintenance;
    LocalDate dateDebut;
    LocalDate dateFin;
    String description;

    @ManyToOne
    Vehicule vehicule;
}