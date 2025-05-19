package ma.maane.examjee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.maane.examjee.dto.CreditDTO;
import ma.maane.examjee.dto.CreditImmobilierDTO;
import ma.maane.examjee.dto.CreditPersonnelDTO;
import ma.maane.examjee.dto.CreditProfessionnelDTO;
import ma.maane.examjee.entities.Credit;
import ma.maane.examjee.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@Tag(name = "Gestion des Crédits", description = "Opérations relatives aux crédits")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping("/personnel")
    @Operation(summary = "Créer un crédit personnel", description = "Enregistre un nouveau crédit personnel")
    public ResponseEntity<CreditDTO> creerCreditPersonnel(@RequestBody CreditPersonnelDTO creditDTO) {
        return ResponseEntity.ok(creditService.creerCreditPersonnel(creditDTO));
    }

    @PostMapping("/immobilier")
    @Operation(summary = "Créer un crédit immobilier", description = "Enregistre un nouveau crédit immobilier")
    public ResponseEntity<CreditDTO> creerCreditImmobilier(@RequestBody CreditImmobilierDTO creditDTO) {
        return ResponseEntity.ok(creditService.creerCreditImmobilier(creditDTO));
    }

    @PostMapping("/professionnel")
    @Operation(summary = "Créer un crédit professionnel", description = "Enregistre un nouveau crédit professionnel")
    public ResponseEntity<CreditDTO> creerCreditProfessionnel(@RequestBody CreditProfessionnelDTO creditDTO) {
        return ResponseEntity.ok(creditService.creerCreditProfessionnel(creditDTO));
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Lister les crédits par statut", description = "Récupère tous les crédits avec un statut donné")
    public ResponseEntity<List<CreditDTO>> listerCreditParStatut(@PathVariable Credit.StatutCredit statut) {
        return ResponseEntity.ok(creditService.listerCreditParStatut(statut));
    }

    @PutMapping("/{creditId}/accepter")
    @Operation(summary = "Accepter un crédit", description = "Change le statut du crédit à ACCEPTE")
    public ResponseEntity<CreditDTO> accepterCredit(@PathVariable Long creditId) {
        return ResponseEntity.ok(creditService.accepterCredit(creditId));
    }
} 