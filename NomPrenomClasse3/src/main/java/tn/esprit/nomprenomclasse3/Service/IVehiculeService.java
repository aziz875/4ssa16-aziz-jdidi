package tn.esprit.nomprenomclasse3.Service;

import tn.esprit.nomprenomclasse3.Domain.Vehicule;

import java.util.List;

public interface IVehiculeService {
    List<Vehicule> findAll();
    Vehicule findById(Long id);
    Vehicule create(Vehicule vehicule);
    Vehicule update(Long id, Vehicule vehicule);
    void deleteById(Long id);

    // Méthodes de compatibilité antérieure
    default Vehicule addVehicule(Vehicule vehicule) { return create(vehicule); }
    default Vehicule updateVehicule(Long id, Vehicule vehicule) { return update(id, vehicule); }
    default void deleteVehicule(Long id) { deleteById(id); }
    default Vehicule getById(Long id) { return findById(id); }
}
