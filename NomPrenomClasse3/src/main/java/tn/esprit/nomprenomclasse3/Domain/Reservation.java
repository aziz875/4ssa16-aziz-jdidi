package tn.esprit.nomprenomclasse3.Domain;

import java.time.LocalDate;

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
@ToString(exclude = "contrat")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idReservation;
    LocalDate dateDebut;
    LocalDate dateFin;

    @Enumerated(value = EnumType.STRING)
    StatutReservation statut;

    @ManyToOne
    Client client;

    @ManyToOne
    Vehicule vehicule;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonIgnore
    Contrat contrat;
}
