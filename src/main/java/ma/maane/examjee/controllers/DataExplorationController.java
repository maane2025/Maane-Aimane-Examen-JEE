package ma.maane.examjee.controllers;

import ma.maane.examjee.entities.*;
import ma.maane.examjee.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/explore")
public class DataExplorationController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditPersonnelRepository creditPersonnelRepository;

    @Autowired
    private CreditImmobilierRepository creditImmobilierRepository;

    @Autowired
    private CreditProfessionnelRepository creditProfessionnelRepository;

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
                
                // Ajouter des détails spécifiques selon le type de crédit
                if (credit instanceof CreditPersonnel creditPersonnel) {
                    creditMap.put("motif", creditPersonnel.getMotif());
                } else if (credit instanceof CreditImmobilier creditImmobilier) {
                    creditMap.put("typeBien", creditImmobilier.getTypeBien());
                } else if (credit instanceof CreditProfessionnel creditProfessionnel) {
                    creditMap.put("motif", creditProfessionnel.getMotif());
                    creditMap.put("raisonSociale", creditProfessionnel.getRaisonSociale());
                }
                
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
                remboursementMap.put("creditClient", remboursement.getCredit().getClient().getNom());
                return remboursementMap;
            })
            .collect(Collectors.toList());
    }

    @GetMapping("/credits-personnels")
    public List<CreditPersonnel> getCreditsPersonnels() {
        return creditPersonnelRepository.findAll();
    }

    @GetMapping("/credits-immobiliers")
    public List<CreditImmobilier> getCreditsImmobiliers() {
        return creditImmobilierRepository.findAll();
    }

    @GetMapping("/credits-professionnels")
    public List<CreditProfessionnel> getCreditsProfessionnels() {
        return creditProfessionnelRepository.findAll();
    }
} 