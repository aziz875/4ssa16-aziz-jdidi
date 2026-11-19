package tn.esprit.nomprenomclasse3.Service.Location.Paiement;

import org.springframework.stereotype.Component;
import tn.esprit.nomprenomclasse3.Domain.ModePaiement;
import tn.esprit.nomprenomclasse3.Domain.Paiement;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Nouveau mode de paiement ajouté par extension SANS modifier le code existant.
 * Mission 2 : WALLET avec 2 % de frais.
 */
@Component
public class WalletPaiementStrategy implements IPaiementStrategy {

    @Override
    public ModePaiement getMode() {
        return ModePaiement.WALLET;
    }

    @Override
    public Paiement traiter(double montantBrut) {
        System.out.println("Paiement par Portefeuille Électronique (WALLET)...");
        double montantFinal = montantBrut * 1.02; // 2 % de frais
        Paiement p = new Paiement();
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(ModePaiement.WALLET);
        p.setMontant(BigDecimal.valueOf(montantFinal));
        return p;
    }
}
