package tn.esprit.nomprenomclasse3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.nomprenomclasse3.Domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
