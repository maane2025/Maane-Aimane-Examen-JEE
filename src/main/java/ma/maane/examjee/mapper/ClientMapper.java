package ma.maane.examjee.mapper;

import ma.maane.examjee.dto.ClientDTO;
import ma.maane.examjee.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClientMapper {

    @Autowired
    private CreditMapper creditMapper;

    public ClientDTO toDTO(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        
        if (client.getCredits() != null) {
            dto.setCredits(client.getCredits().stream()
                .map(creditMapper::toDTO)
                .collect(Collectors.toList()));
            dto.setNombreCredits(client.getCredits().size());
        }

        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        
        if (dto.getCredits() != null) {
            client.setCredits(dto.getCredits().stream()
                .map(creditMapper::toEntity)
                .collect(Collectors.toList()));
        }

        return client;
    }
} 