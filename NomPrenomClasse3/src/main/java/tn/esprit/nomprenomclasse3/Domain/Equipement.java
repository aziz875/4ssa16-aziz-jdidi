package tn.esprit.nomprenomclasse3.Domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    Integer quantite;

    @ManyToMany(mappedBy = "equipements")
    @JsonIgnore
    List<Vehicule> vehicules;
}
