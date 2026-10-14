package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEquipement;
    String libelle;

    // Equipement (*) <--> (*) Vehicule — côté inverse — Cascade: Aucune
    @ManyToMany(mappedBy = "equipements")
    Set<Vehicule> vehicules = new HashSet<>();
}
