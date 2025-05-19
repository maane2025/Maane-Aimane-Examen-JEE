package ma.maane.examjee.repositories;

import ma.maane.examjee.entities.Remboursement;
import ma.maane.examjee.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCredit(Credit credit);
    List<Remboursement> findByType(Remboursement.TypeRemboursement type);
} 