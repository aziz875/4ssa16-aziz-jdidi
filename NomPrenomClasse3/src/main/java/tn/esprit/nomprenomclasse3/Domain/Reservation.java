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
@ToString(exclude = {"client", "vehicule", "contrat"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idReservation;
    LocalDate dateDebut;
    LocalDate dateFin;

    @Enumerated(value = EnumType.STRING)
    StatutReservation statut;

    // Reservation (*) --> (1) Client
    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    // Reservation (*) --> (1) Vehicule
    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    Vehicule vehicule;

    // Reservation (1) --> (1) Contrat
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    Contrat contrat;
}
