package ma.maane.examjee.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "credits_professionnels")
public class CreditProfessionnel extends Credit {
    @Column(nullable = false)
    private String motif;

    @Column(name = "raison_sociale", nullable = false)
    private String raisonSociale;
} 