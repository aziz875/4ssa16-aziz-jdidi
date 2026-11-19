package tn.esprit.nomprenomclasse3.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nomprenomclasse3.Domain.Client;
import tn.esprit.nomprenomclasse3.Exception.ResourceNotFoundException;
import tn.esprit.nomprenomclasse3.Repository.ClientRepository;
import tn.esprit.nomprenomclasse3.Service.IClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'identifiant " + id));
    }

    @Override
    public Client create(Client client) {
        if (client.getIdClient() > 0) {
            throw new IllegalArgumentException("L'identifiant ne doit pas être renseigné lors de la création");
        }
        validateClient(client);
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        Client existing = findById(id);
        validateClient(client);
        existing.setNom(client.getNom());
        existing.setPrenom(client.getPrenom());
        existing.setEmail(client.getEmail());
        existing.setTelephone(client.getTelephone());
        existing.setNumPermis(client.getNumPermis());
        existing.setDateInscription(client.getDateInscription());
        return clientRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        Client existing = findById(id);
        clientRepository.delete(existing);
    }

    private void validateClient(Client client) {
        if (client.getNumPermis() == null || client.getNumPermis().trim().isEmpty()) {
            throw new IllegalArgumentException("Le numéro de permis est obligatoire");
        }
        if (client.getNom() == null || client.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du client est obligatoire");
        }
    }
}
