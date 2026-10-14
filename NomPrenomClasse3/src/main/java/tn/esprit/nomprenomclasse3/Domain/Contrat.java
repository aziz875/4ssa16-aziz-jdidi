package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idContrat;
    LocalDate dateSignature;
    BigDecimal montantTotal;
    boolean valide;

    // Contrat (1) <--> (1) Reservation — côté propriétaire — Cascade: Aucune
    @OneToOne
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    // Contrat (1) --> (*) Paiement — Cascade: ALL + orphanRemoval
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Paiement> paiements = new ArrayList<>();
}
