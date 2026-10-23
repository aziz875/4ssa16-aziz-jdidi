package tn.esprit.nomprenomclasse3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.nomprenomclasse3.Domain.Client;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
