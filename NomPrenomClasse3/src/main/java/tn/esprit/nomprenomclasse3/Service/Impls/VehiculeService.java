package tn.esprit.nomprenomclasse3.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;
import tn.esprit.nomprenomclasse3.Exception.ResourceNotFoundException;
import tn.esprit.nomprenomclasse3.Repository.IVehiculeRepository;
import tn.esprit.nomprenomclasse3.Service.IVehiculeService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeService implements IVehiculeService {

    private final IVehiculeRepository iVehiculeRepository;

    @Override
    public List<Vehicule> findAll() {
        return iVehiculeRepository.findAll();
    }

    @Override
    public Vehicule findById(Long id) {
        return iVehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable avec l'identifiant " + id));
    }

    @Override
    public Vehicule create(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null && vehicule.getIdVehicule() > 0) {
            throw new IllegalArgumentException("L'identifiant ne doit pas être renseigné lors de la création");
        }
        if (vehicule.getTarifJournalier() != null && vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
        return iVehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        Vehicule existing = findById(id);
        if (vehicule.getTarifJournalier() != null && vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
        existing.setImmatriculation(vehicule.getImmatriculation());
        existing.setMarque(vehicule.getMarque());
        existing.setModele(vehicule.getModele());
        existing.setCategorie(vehicule.getCategorie());
        existing.setTarifJournalier(vehicule.getTarifJournalier());
        existing.setStatut(vehicule.getStatut());
        if (vehicule.getAgence() != null) {
            existing.setAgence(vehicule.getAgence());
        }
        return iVehiculeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        Vehicule existing = findById(id);
        iVehiculeRepository.delete(existing);
    }
}
