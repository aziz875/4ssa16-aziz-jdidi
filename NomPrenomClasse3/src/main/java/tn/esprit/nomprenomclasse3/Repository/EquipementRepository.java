package tn.esprit.nomprenomclasse3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.nomprenomclasse3.Domain.Equipement;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
