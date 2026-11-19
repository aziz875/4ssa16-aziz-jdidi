package tn.esprit.nomprenomclasse3.Service.Location.Paiement;

import org.springframework.stereotype.Component;
import tn.esprit.nomprenomclasse3.Domain.ModePaiement;
import tn.esprit.nomprenomclasse3.Domain.Paiement;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class EspecesPaiementStrategy implements IPaiementStrategy {

    @Override
    public ModePaiement getMode() {
        return ModePaiement.ESPECES;
    }

    @Override
    public Paiement traiter(double montantBrut) {
        System.out.println("Encaissement en espèces à l'agence");
        Paiement p = new Paiement();
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(ModePaiement.ESPECES);
        p.setMontant(BigDecimal.valueOf(montantBrut));
        return p;
    }
}
