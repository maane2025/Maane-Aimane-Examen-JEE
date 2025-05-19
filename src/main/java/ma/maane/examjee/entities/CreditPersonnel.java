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
@Table(name = "credits_personnels")
public class CreditPersonnel extends Credit {
    @Column(nullable = false)
    private String motif;
} 