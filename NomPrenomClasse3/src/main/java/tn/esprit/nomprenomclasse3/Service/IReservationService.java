package tn.esprit.nomprenomclasse3.Service;

import tn.esprit.nomprenomclasse3.Domain.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> findAll();
    Reservation findById(Long id);
    Reservation create(Reservation reservation);
    Reservation update(Long id, Reservation reservation);
    void deleteById(Long id);
}
