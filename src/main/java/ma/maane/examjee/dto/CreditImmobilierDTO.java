package ma.maane.examjee.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ma.maane.examjee.entities.CreditImmobilier;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CreditImmobilierDTO extends CreditDTO {
    private CreditImmobilier.TypeBien typeBien;
} 