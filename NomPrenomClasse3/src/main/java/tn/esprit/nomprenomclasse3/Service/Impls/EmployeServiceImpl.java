package tn.esprit.nomprenomclasse3.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Agence;
import tn.esprit.nomprenomclasse3.Domain.Employe;
import tn.esprit.nomprenomclasse3.Exception.ResourceNotFoundException;
import tn.esprit.nomprenomclasse3.Repository.AgenceRepository;
import tn.esprit.nomprenomclasse3.Repository.EmployeRepository;
import tn.esprit.nomprenomclasse3.Service.IEmployeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé introuvable avec l'identifiant " + id));
    }

    @Override
    public Employe create(Employe employe) {
        if (employe.getIdEmployee() > 0) {
            throw new IllegalArgumentException("L'identifiant ne doit pas être renseigné lors de la création");
        }
        if (employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        if (employe.getAgence() != null && employe.getAgence().getIdAgence() > 0) {
            Agence agence = agenceRepository.findById(employe.getAgence().getIdAgence())
                    .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'identifiant " + employe.getAgence().getIdAgence()));
            employe.setAgence(agence);
        }
        return employeRepository.save(employe);
    }

    @Override
    public Employe update(Long id, Employe employe) {
        Employe existing = findById(id);
        if (employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        existing.setNom(employe.getNom());
        existing.setPrenom(employe.getPrenom());
        existing.setRole(employe.getRole());
        if (employe.getAgence() != null && employe.getAgence().getIdAgence() > 0) {
            Agence agence = agenceRepository.findById(employe.getAgence().getIdAgence())
                    .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'identifiant " + employe.getAgence().getIdAgence()));
            existing.setAgence(agence);
        }
        return employeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        Employe existing = findById(id);
        employeRepository.delete(existing);
    }
}
