package ma.maane.examjee.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "credits")
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_demande", nullable = false)
    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCredit statut;

    @Column(name = "date_acceptation")
    private LocalDate dateAcceptation;

    @Column(nullable = false)
    private BigDecimal montant;

    @Column(name = "duree_remboursement", nullable = false)
    private Integer dureeRemboursement;

    @Column(name = "taux_interet", nullable = false)
    private BigDecimal tauxInteret;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Remboursement> remboursements;

    public enum StatutCredit {
        EN_COURS,
        ACCEPTE,
        REJETE
    }
} 