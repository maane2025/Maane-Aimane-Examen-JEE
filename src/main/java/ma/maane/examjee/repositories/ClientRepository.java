package ma.maane.examjee.repositories;

import ma.maane.examjee.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Méthodes de requête personnalisées peuvent être ajoutées ici si nécessaire
    Client findByEmail(String email);
} 