package ma.maane.examjee.controllers;

import ma.maane.examjee.entities.Client;
import ma.maane.examjee.entities.Credit;
import ma.maane.examjee.repositories.ClientRepository;
import ma.maane.examjee.repositories.CreditRepository;
import ma.maane.examjee.repositories.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
public class TestDataController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private RemboursementRepository remboursementRepository;

    @GetMapping("/clients")
    public List<Map<String, Object>> getAllClients() {
        return clientRepository.findAll().stream()
            .map(client -> {
                Map<String, Object> clientMap = new HashMap<>();
                clientMap.put("id", client.getId());
                clientMap.put("nom", client.getNom());
                clientMap.put("email", client.getEmail());
                clientMap.put("nombreCredits", client.getCredits() != null ? client.getCredits().size() : 0);
                return clientMap;
            })
            .collect(Collectors.toList());
    }

    @GetMapping("/credits")
    public List<Map<String, Object>> getAllCredits() {
        return creditRepository.findAll().stream()
            .map(credit -> {
                Map<String, Object> creditMap = new HashMap<>();
                creditMap.put("id", credit.getId());
                creditMap.put("client", credit.getClient().getNom());
                creditMap.put("montant", credit.getMontant());
                creditMap.put("statut", credit.getStatut());
                creditMap.put("type", credit.getClass().getSimpleName());
                return creditMap;
            })
            .collect(Collectors.toList());
    }

    @GetMapping("/remboursements")
    public List<Map<String, Object>> getAllRemboursements() {
        return remboursementRepository.findAll().stream()
            .map(remboursement -> {
                Map<String, Object> remboursementMap = new HashMap<>();
                remboursementMap.put("id", remboursement.getId());
                remboursementMap.put("montant", remboursement.getMontant());
                remboursementMap.put("date", remboursement.getDate());
                remboursementMap.put("type", remboursement.getType());
                remboursementMap.put("creditId", remboursement.getCredit().getId());
                return remboursementMap;
            })
            .collect(Collectors.toList());
    }
} 