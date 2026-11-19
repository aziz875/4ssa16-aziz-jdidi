package tn.esprit.nomprenomclasse3.Service.Location.Paiement;

import org.springframework.stereotype.Component;
import tn.esprit.nomprenomclasse3.Domain.ModePaiement;
import tn.esprit.nomprenomclasse3.Domain.Paiement;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class VirementPaiementStrategy implements IPaiementStrategy {

    @Override
    public ModePaiement getMode() {
        return ModePaiement.VIREMENT;
    }

    @Override
    public Paiement traiter(double montantBrut) {
        System.out.println("Attente du virement...");
        Paiement p = new Paiement();
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(ModePaiement.VIREMENT);
        p.setMontant(BigDecimal.valueOf(montantBrut));
        return p;
    }
}
