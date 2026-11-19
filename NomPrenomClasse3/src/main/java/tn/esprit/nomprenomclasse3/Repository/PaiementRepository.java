package tn.esprit.nomprenomclasse3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.nomprenomclasse3.Domain.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
