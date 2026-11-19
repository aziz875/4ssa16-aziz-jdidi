package tn.esprit.nomprenomclasse3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.nomprenomclasse3.Domain.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
