package tn.esprit.nomprenomclasse3.Service.Impls;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Vehicule;
import tn.esprit.nomprenomclasse3.Repository.IVehiculeRepository;
import tn.esprit.nomprenomclasse3.Service.IVehiculeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeService implements IVehiculeService {

    private final IVehiculeRepository iVehiculeRepository;
    @Override
    public Vehicule addVehicule(Vehicule vehicule) {

        return iVehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        return iVehiculeRepository.save(vehicule);
    }

    @Override

    public void deleteVehicule(Long id) {
        iVehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule getById(Long id) {
        return iVehiculeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("vehicule not found"));
        //return iVehiculeRepository.getById(id);
    }


    @Override
    public List<Vehicule> findAll() {
        return iVehiculeRepository.findAll();
    }
}
