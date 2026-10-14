package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "vehicules")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEquipement;
    String libelle;

    // Equipement (*) <--> (*) Vehicule (inverse side)
    @ManyToMany(mappedBy = "equipements")
    List<Vehicule> vehicules;
}
