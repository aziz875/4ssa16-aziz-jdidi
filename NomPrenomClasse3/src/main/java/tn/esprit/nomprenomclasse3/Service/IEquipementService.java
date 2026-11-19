package tn.esprit.nomprenomclasse3.Service;

import tn.esprit.nomprenomclasse3.Domain.Equipement;

import java.util.List;

public interface IEquipementService {
    List<Equipement> findAll();
    Equipement findById(Long id);
    Equipement create(Equipement equipement);
    Equipement update(Long id, Equipement equipement);
    void deleteById(Long id);
}
