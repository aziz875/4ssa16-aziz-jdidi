package tn.esprit.nomprenomclasse3.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Paiement;
import tn.esprit.nomprenomclasse3.Exception.ResourceNotFoundException;
import tn.esprit.nomprenomclasse3.Repository.PaiementRepository;
import tn.esprit.nomprenomclasse3.Service.IPaiementService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement findById(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable avec l'identifiant " + id));
    }
}
