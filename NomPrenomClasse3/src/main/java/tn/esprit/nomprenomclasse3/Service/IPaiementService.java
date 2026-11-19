package tn.esprit.nomprenomclasse3.Service;

import tn.esprit.nomprenomclasse3.Domain.Paiement;

import java.util.List;

public interface IPaiementService {
    List<Paiement> findAll();
    Paiement findById(Long id);
}
