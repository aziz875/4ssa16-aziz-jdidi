package tn.esprit.nomprenomclasse3.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Equipement;
import tn.esprit.nomprenomclasse3.Exception.ResourceNotFoundException;
import tn.esprit.nomprenomclasse3.Repository.EquipementRepository;
import tn.esprit.nomprenomclasse3.Service.IEquipementService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final EquipementRepository equipementRepository;

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement findById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable avec l'identifiant " + id));
    }

    @Override
    public Equipement create(Equipement equipement) {
        if (equipement.getIdEquipement() > 0) {
            throw new IllegalArgumentException("L'identifiant ne doit pas être renseigné lors de la création");
        }
        validateEquipement(equipement);
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement update(Long id, Equipement equipement) {
        Equipement existing = findById(id);
        validateEquipement(equipement);
        existing.setLibelle(equipement.getLibelle());
        existing.setQuantite(equipement.getQuantite());
        return equipementRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        Equipement existing = findById(id);
        equipementRepository.delete(existing);
    }

    private void validateEquipement(Equipement equipement) {
        if (equipement.getLibelle() == null || equipement.getLibelle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de l'équipement ne peut pas être vide");
        }
        if (equipement.getQuantite() != null && equipement.getQuantite() < 0) {
            throw new IllegalArgumentException("La quantité de l'équipement ne peut pas être négative");
        }
    }
}
