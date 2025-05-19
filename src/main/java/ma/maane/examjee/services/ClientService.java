package ma.maane.examjee.services;

import ma.maane.examjee.dto.ClientDTO;
import ma.maane.examjee.entities.Client;
import ma.maane.examjee.mapper.ClientMapper;
import ma.maane.examjee.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Transactional
    public ClientDTO creerClient(ClientDTO clientDTO) {
        // Convertir le DTO en entité
        Client client = clientMapper.toEntity(clientDTO);
        
        // Sauvegarder le client
        Client savedClient = clientRepository.save(client);
        
        // Convertir et retourner le DTO
        return clientMapper.toDTO(savedClient);
    }

    @Transactional(readOnly = true)
    public ClientDTO obtenirClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new IllegalArgumentException("Client non trouvé avec l'ID : " + clientId));
        
        return clientMapper.toDTO(client);
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> listerClients() {
        return clientRepository.findAll().stream()
            .map(clientMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public void supprimerClient(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new IllegalArgumentException("Client non trouvé avec l'ID : " + clientId);
        }
        clientRepository.deleteById(clientId);
    }
} 