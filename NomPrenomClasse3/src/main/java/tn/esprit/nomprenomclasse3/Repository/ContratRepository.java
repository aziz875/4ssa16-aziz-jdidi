package tn.esprit.nomprenomclasse3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.nomprenomclasse3.Domain.Contrat;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;

public interface ContratRepository extends JpaRepository<Contrat,Long> {
}
