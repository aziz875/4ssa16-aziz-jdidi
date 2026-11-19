package tn.esprit.nomprenomclasse3.Service;

import tn.esprit.nomprenomclasse3.Domain.Contrat;

import java.util.List;

public interface IContratService {
    List<Contrat> findAll();
    Contrat findById(Long id);
    Contrat create(Contrat contrat);
    Contrat update(Long id, Contrat contrat);
    void deleteById(Long id);
}
