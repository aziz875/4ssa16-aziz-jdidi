package tn.esprit.nomprenomclasse3.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "contrat")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idPaiement;
    BigDecimal montant;
    LocalDate datePaiement;

    @Enumerated(value = EnumType.STRING)
    ModePaiement modePaiement;

    // Paiement (*) --> (1) Contrat
    @ManyToOne
    @JoinColumn(name = "contrat_id")
    Contrat contrat;
}
