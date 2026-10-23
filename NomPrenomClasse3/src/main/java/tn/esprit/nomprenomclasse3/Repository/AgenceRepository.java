package tn.esprit.nomprenomclasse3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.nomprenomclasse3.Domain.Agence;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;

public interface AgenceRepository extends JpaRepository<Agence,Long> {
}
