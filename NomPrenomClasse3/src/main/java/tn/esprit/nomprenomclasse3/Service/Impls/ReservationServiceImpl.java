package tn.esprit.nomprenomclasse3.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Client;
import tn.esprit.nomprenomclasse3.Domain.Reservation;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;
import tn.esprit.nomprenomclasse3.Exception.ResourceNotFoundException;
import tn.esprit.nomprenomclasse3.Repository.ClientRepository;
import tn.esprit.nomprenomclasse3.Repository.IVehiculeRepository;
import tn.esprit.nomprenomclasse3.Repository.ReservationRepository;
import tn.esprit.nomprenomclasse3.Service.IReservationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final IVehiculeRepository vehiculeRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable avec l'identifiant " + id));
    }

    @Override
    public Reservation create(Reservation reservation) {
        if (reservation.getIdReservation() > 0) {
            throw new IllegalArgumentException("L'identifiant ne doit pas être renseigné lors de la création");
        }
        validateReservation(reservation);
        resolveAssociations(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {
        Reservation existing = findById(id);
        validateReservation(reservation);
        existing.setDateDebut(reservation.getDateDebut());
        existing.setDateFin(reservation.getDateFin());
        existing.setStatut(reservation.getStatut());
        resolveAssociations(reservation);
        if (reservation.getClient() != null) {
            existing.setClient(reservation.getClient());
        }
        if (reservation.getVehicule() != null) {
            existing.setVehicule(reservation.getVehicule());
        }
        return reservationRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        Reservation existing = findById(id);
        reservationRepository.delete(existing);
    }

    private void validateReservation(Reservation reservation) {
        if (reservation.getDateDebut() != null && reservation.getDateFin() != null
                && reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
    }

    private void resolveAssociations(Reservation reservation) {
        if (reservation.getClient() != null && reservation.getClient().getIdClient() > 0) {
            Client client = clientRepository.findById(reservation.getClient().getIdClient())
                    .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'identifiant " + reservation.getClient().getIdClient()));
            reservation.setClient(client);
        }
        if (reservation.getVehicule() != null && reservation.getVehicule().getIdVehicule() != null && reservation.getVehicule().getIdVehicule() > 0) {
            Vehicule vehicule = vehiculeRepository.findById(reservation.getVehicule().getIdVehicule())
                    .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable avec l'identifiant " + reservation.getVehicule().getIdVehicule()));
            reservation.setVehicule(vehicule);
        }
    }
}
