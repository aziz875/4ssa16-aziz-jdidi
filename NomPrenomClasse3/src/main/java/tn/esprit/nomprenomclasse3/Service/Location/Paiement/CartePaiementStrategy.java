package tn.esprit.nomprenomclasse3.Service.Location.Paiement;

import org.springframework.stereotype.Component;
import tn.esprit.nomprenomclasse3.Domain.ModePaiement;
import tn.esprit.nomprenomclasse3.Domain.Paiement;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CartePaiementStrategy implements IPaiementStrategy {

    @Override
    public ModePaiement getMode() {
        return ModePaiement.CARTE;
    }

    @Override
    public Paiement traiter(double montantBrut) {
        System.out.println("Débit de la carte bancaire...");
        double montantFinal = montantBrut * 1.01; // 1 % de frais
        Paiement p = new Paiement();
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(ModePaiement.CARTE);
        p.setMontant(BigDecimal.valueOf(montantFinal));
        return p;
    }
}
