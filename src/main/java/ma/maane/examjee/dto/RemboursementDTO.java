package ma.maane.examjee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.maane.examjee.entities.Remboursement;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemboursementDTO {
    private Long id;
    private LocalDate date;
    private BigDecimal montant;
    private Remboursement.TypeRemboursement type;
    private Long creditId;
} 