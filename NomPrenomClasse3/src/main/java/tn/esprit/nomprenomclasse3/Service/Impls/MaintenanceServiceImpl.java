package tn.esprit.nomprenomclasse3.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Maintenance;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;
import tn.esprit.nomprenomclasse3.Exception.ResourceNotFoundException;
import tn.esprit.nomprenomclasse3.Repository.IVehiculeRepository;
import tn.esprit.nomprenomclasse3.Repository.MaintenanceRepository;
import tn.esprit.nomprenomclasse3.Service.IMaintenanceService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final IVehiculeRepository vehiculeRepository;

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance introuvable avec l'identifiant " + id));
    }

    @Override
    public Maintenance create(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() > 0) {
            throw new IllegalArgumentException("L'identifiant ne doit pas être renseigné lors de la création");
        }
        validateMaintenance(maintenance);
        resolveVehicule(maintenance);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance update(Long id, Maintenance maintenance) {
        Maintenance existing = findById(id);
        validateMaintenance(maintenance);
        existing.setDateDebut(maintenance.getDateDebut());
        existing.setDateFin(maintenance.getDateFin());
        existing.setDescription(maintenance.getDescription());
        existing.setCout(maintenance.getCout());
        resolveVehicule(maintenance);
        if (maintenance.getVehicule() != null) {
            existing.setVehicule(maintenance.getVehicule());
        }
        return maintenanceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        Maintenance existing = findById(id);
        maintenanceRepository.delete(existing);
    }

    private void validateMaintenance(Maintenance maintenance) {
        if (maintenance.getCout() != null && maintenance.getCout().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le coût de maintenance ne peut pas être négatif");
        }
        if (maintenance.getDateDebut() != null && maintenance.getDateFin() != null
                && maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début");
        }
    }

    private void resolveVehicule(Maintenance maintenance) {
        if (maintenance.getVehicule() != null && maintenance.getVehicule().getIdVehicule() != null
                && maintenance.getVehicule().getIdVehicule() > 0) {
            Vehicule vehicule = vehiculeRepository.findById(maintenance.getVehicule().getIdVehicule())
                    .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable avec l'identifiant " + maintenance.getVehicule().getIdVehicule()));
            maintenance.setVehicule(vehicule);
        }
    }
}
