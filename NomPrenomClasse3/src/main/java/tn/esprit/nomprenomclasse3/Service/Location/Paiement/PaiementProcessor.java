package tn.esprit.nomprenomclasse3.Service.Location.Paiement;

import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.ModePaiement;
import tn.esprit.nomprenomclasse3.Domain.Paiement;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Gestionnaire de paiement conforme à l'OCP.
 * Fermé à la modification, ouvert à l'extension : tout nouveau mode
 * de paiement ajouté est automatiquement détecté et injecté par Spring.
 */
@Service
public class PaiementProcessor {

    private final Map<ModePaiement, IPaiementStrategy> strategies = new EnumMap<>(ModePaiement.class);

    public PaiementProcessor(List<IPaiementStrategy> strategyList) {
        for (IPaiementStrategy strategy : strategyList) {
            strategies.put(strategy.getMode(), strategy);
        }
    }

    public Paiement executerPaiement(ModePaiement mode, double montantBrut) {
        IPaiementStrategy strategy = strategies.get(mode);
        if (strategy == null) {
            throw new IllegalArgumentException("Mode de paiement non supporté : " + mode);
        }
        return strategy.traiter(montantBrut);
    }
}
