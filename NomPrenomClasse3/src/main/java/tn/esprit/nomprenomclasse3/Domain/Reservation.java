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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idReservation;
    LocalDate dateDebut;
    LocalDate dateFin;

    @Enumerated(value = EnumType.STRING)
    StatutReservation statut;

    // Reservation (*) --> (1) Client — Cascade: Aucune
    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    // Reservation (*) --> (1) Vehicule — Cascade: Aucune (unidirectionnelle)
    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    Vehicule vehicule;

    // Reservation (1) <--> (1) Contrat — Cascade: ALL côté Reservation (inverse)
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    Contrat contrat;
}
