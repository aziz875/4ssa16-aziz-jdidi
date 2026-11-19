package tn.esprit.nomprenomclasse3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.nomprenomclasse3.Domain.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
