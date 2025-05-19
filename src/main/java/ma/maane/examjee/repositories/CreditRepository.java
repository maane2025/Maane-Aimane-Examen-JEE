package ma.maane.examjee.repositories;

import ma.maane.examjee.entities.Credit;
import ma.maane.examjee.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClient(Client client);
    List<Credit> findByStatut(Credit.StatutCredit statut);
} 