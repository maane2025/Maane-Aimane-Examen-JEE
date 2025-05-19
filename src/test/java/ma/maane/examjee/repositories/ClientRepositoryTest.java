package ma.maane.examjee.repositories;

import ma.maane.examjee.entities.Client;
import ma.maane.examjee.entities.Credit;
import ma.maane.examjee.entities.CreditPersonnel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "ma.maane.examjee")
@ContextConfiguration
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditRepository creditRepository;

    private Client testClient;

    @BeforeEach
    public void setUp() {
        // Créer un client de test
        testClient = new Client();
        testClient.setNom("Test Client");
        testClient.setEmail("test.client@example.com");
        entityManager.persist(testClient);
        entityManager.flush();
    }

    @Test
    public void testSaveClient() {
        // Vérifier que le client est sauvegardé correctement
        Client found = clientRepository.findByEmail("test.client@example.com");
        assertThat(found).isNotNull();
        assertThat(found.getNom()).isEqualTo("Test Client");
    }

    @Test
    public void testAddCreditToClient() {
        // Créer un crédit personnel pour le client
        CreditPersonnel credit = new CreditPersonnel();
        credit.setClient(testClient);
        credit.setDateDemande(LocalDate.now());
        credit.setStatut(Credit.StatutCredit.EN_COURS);
        credit.setMontant(BigDecimal.valueOf(5000));
        credit.setDureeRemboursement(24);
        credit.setTauxInteret(BigDecimal.valueOf(4.5));
        credit.setMotif("Test Motif");

        // Sauvegarder le crédit
        entityManager.persist(credit);
        entityManager.flush();

        // Récupérer le client et vérifier ses crédits
        Client foundClient = clientRepository.findByEmail("test.client@example.com");
        List<Credit> credits = creditRepository.findByClient(foundClient);

        assertThat(credits).hasSize(1);
        assertThat(credits.get(0)).isInstanceOf(CreditPersonnel.class);
        assertThat(((CreditPersonnel)credits.get(0)).getMotif()).isEqualTo("Test Motif");
    }

    @Test
    public void testUniqueEmailConstraint() {
        // Tenter de créer un client avec un email existant
        Client duplicateClient = new Client();
        duplicateClient.setNom("Duplicate Client");
        duplicateClient.setEmail("test.client@example.com");

        // Vérifier que cela lève une exception
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            entityManager.persist(duplicateClient);
            entityManager.flush();
        });
    }
} 