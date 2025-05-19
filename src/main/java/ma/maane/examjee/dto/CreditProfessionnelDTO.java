package ma.maane.examjee.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CreditProfessionnelDTO extends CreditDTO {
    private String motif;
    private String raisonSociale;
} 