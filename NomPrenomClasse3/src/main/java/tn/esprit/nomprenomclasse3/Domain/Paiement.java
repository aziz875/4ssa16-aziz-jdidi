package tn.esprit.nomprenomclasse3.Domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idPaiement;
    BigDecimal montant;
    LocalDate datePaiement;

    @Enumerated(value = EnumType.STRING)
    ModePaiement modePaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("contrat-paiements")
    Contrat contrat;
}
