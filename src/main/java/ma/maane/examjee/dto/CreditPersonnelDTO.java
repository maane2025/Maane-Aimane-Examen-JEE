package ma.maane.examjee.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CreditPersonnelDTO extends CreditDTO {
    private String motif;
} 