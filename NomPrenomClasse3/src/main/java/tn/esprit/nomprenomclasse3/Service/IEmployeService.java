package tn.esprit.nomprenomclasse3.Service;

import tn.esprit.nomprenomclasse3.Domain.Employe;

import java.util.List;

public interface IEmployeService {
    List<Employe> findAll();
    Employe findById(Long id);
    Employe create(Employe employe);
    Employe update(Long id, Employe employe);
    void deleteById(Long id);
}
