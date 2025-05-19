package ma.maane.examjee.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "credits_immobiliers")
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    @Column(name = "type_bien", nullable = false)
    private TypeBien typeBien;

    public enum TypeBien {
        APPARTEMENT,
        MAISON,
        LOCAL_COMMERCIAL
    }
} 