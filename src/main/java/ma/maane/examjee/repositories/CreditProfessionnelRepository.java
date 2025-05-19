package ma.maane.examjee.repositories;

import ma.maane.examjee.entities.CreditProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Long> {
    List<CreditProfessionnel> findByMotif(String motif);
    List<CreditProfessionnel> findByRaisonSociale(String raisonSociale);
} 