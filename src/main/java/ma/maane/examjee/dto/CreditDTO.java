package ma.maane.examjee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.maane.examjee.entities.Credit;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private Credit.StatutCredit statut;
    private LocalDate dateAcceptation;
    private BigDecimal montant;
    private Integer dureeRemboursement;
    private BigDecimal tauxInteret;
    private Long clientId;
} 