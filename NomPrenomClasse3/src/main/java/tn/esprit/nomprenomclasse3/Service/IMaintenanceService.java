package tn.esprit.nomprenomclasse3.Service;

import tn.esprit.nomprenomclasse3.Domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    List<Maintenance> findAll();
    Maintenance findById(Long id);
    Maintenance create(Maintenance maintenance);
    Maintenance update(Long id, Maintenance maintenance);
    void deleteById(Long id);
}
