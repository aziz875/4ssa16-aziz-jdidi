package tn.esprit.nomprenomclasse3.Domain;

import java.math.BigDecimal;
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
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idContrat;
    LocalDate dateSignature;
    BigDecimal montantTotal;
    Boolean valide;

    @OneToOne
    Reservation reservation;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contrat")
    Paiement paiement;
}