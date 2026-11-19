package tn.esprit.nomprenomclasse3.Service.Location.Paiement;

import tn.esprit.nomprenomclasse3.Domain.ModePaiement;
import tn.esprit.nomprenomclasse3.Domain.Paiement;

/**
 * Contrat d'abstraction pour un mode de paiement (Principe OCP).
 */
public interface IPaiementStrategy {
    ModePaiement getMode();
    Paiement traiter(double montantBrut);
}
