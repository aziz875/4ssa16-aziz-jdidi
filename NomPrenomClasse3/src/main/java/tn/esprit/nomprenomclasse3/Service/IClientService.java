package tn.esprit.nomprenomclasse3.Service;

import tn.esprit.nomprenomclasse3.Domain.Client;

import java.util.List;

public interface IClientService {
    List<Client> findAll();
    Client findById(Long id);
    Client create(Client client);
    Client update(Long id, Client client);
    void deleteById(Long id);
}
