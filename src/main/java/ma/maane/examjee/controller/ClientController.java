package ma.maane.examjee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.maane.examjee.dto.ClientDTO;
import ma.maane.examjee.dto.CreditDTO;
import ma.maane.examjee.services.ClientService;
import ma.maane.examjee.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Gestion des Clients", description = "Opérations relatives aux clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CreditService creditService;

    @PostMapping
    @Operation(summary = "Créer un nouveau client", description = "Enregistre un nouveau client dans le système")
    public ResponseEntity<ClientDTO> creerClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.creerClient(clientDTO));
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Obtenir les détails d'un client", description = "Récupère les informations d'un client par son ID")
    public ResponseEntity<ClientDTO> obtenirClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.obtenirClient(clientId));
    }

    @GetMapping("/{clientId}/credits")
    @Operation(summary = "Lister les crédits d'un client", description = "Récupère tous les crédits associés à un client")
    public ResponseEntity<List<CreditDTO>> listerCreditsClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.listerCreditsPourClient(clientId));
    }

    @GetMapping
    @Operation(summary = "Lister tous les clients", description = "Récupère la liste de tous les clients")
    public ResponseEntity<List<ClientDTO>> listerClients() {
        return ResponseEntity.ok(clientService.listerClients());
    }
} 