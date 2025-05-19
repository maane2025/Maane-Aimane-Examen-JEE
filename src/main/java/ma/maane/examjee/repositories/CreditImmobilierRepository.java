package ma.maane.examjee.repositories;

import ma.maane.examjee.entities.CreditImmobilier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
    List<CreditImmobilier> findByTypeBien(CreditImmobilier.TypeBien typeBien);
} 