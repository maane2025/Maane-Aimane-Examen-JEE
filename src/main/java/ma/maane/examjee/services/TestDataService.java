package ma.maane.examjee.services;

import ma.maane.examjee.entities.*;
import ma.maane.examjee.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TestDataService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditPersonnelRepository creditPersonnelRepository;

    @Autowired
    private CreditImmobilierRepository creditImmobilierRepository;

    @Autowired
    private CreditProfessionnelRepository creditProfessionnelRepository;

    @Autowired
    private RemboursementRepository remboursementRepository;

    @Transactional
    public void genererDonneesTest() {
        // Créer des clients
        Client client1 = new Client();
        client1.setNom("Jean Dupont");
        client1.setEmail("jean.dupont@example.com");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setNom("Marie Martin");
        client2.setEmail("marie.martin@example.com");
        clientRepository.save(client2);

        // Créer des crédits personnels
        CreditPersonnel creditPersonnel1 = new CreditPersonnel();
        creditPersonnel1.setClient(client1);
        creditPersonnel1.setDateDemande(LocalDate.now());
        creditPersonnel1.setStatut(Credit.StatutCredit.EN_COURS);
        creditPersonnel1.setMontant(BigDecimal.valueOf(10000));
        creditPersonnel1.setDureeRemboursement(36);
        creditPersonnel1.setTauxInteret(BigDecimal.valueOf(4.5));
        creditPersonnel1.setMotif("Achat de voiture");
        creditPersonnelRepository.save(creditPersonnel1);

        // Créer des crédits immobiliers
        CreditImmobilier creditImmobilier1 = new CreditImmobilier();
        creditImmobilier1.setClient(client2);
        creditImmobilier1.setDateDemande(LocalDate.now());
        creditImmobilier1.setStatut(Credit.StatutCredit.ACCEPTE);
        creditImmobilier1.setMontant(BigDecimal.valueOf(200000));
        creditImmobilier1.setDureeRemboursement(240);
        creditImmobilier1.setTauxInteret(BigDecimal.valueOf(2.1));
        creditImmobilier1.setTypeBien(CreditImmobilier.TypeBien.APPARTEMENT);
        creditImmobilierRepository.save(creditImmobilier1);

        // Créer des crédits professionnels
        CreditProfessionnel creditProfessionnel1 = new CreditProfessionnel();
        creditProfessionnel1.setClient(client1);
        creditProfessionnel1.setDateDemande(LocalDate.now());
        creditProfessionnel1.setStatut(Credit.StatutCredit.EN_COURS);
        creditProfessionnel1.setMontant(BigDecimal.valueOf(50000));
        creditProfessionnel1.setDureeRemboursement(60);
        creditProfessionnel1.setTauxInteret(BigDecimal.valueOf(3.5));
        creditProfessionnel1.setMotif("Extension d'entreprise");
        creditProfessionnel1.setRaisonSociale("Tech Innovations SARL");
        creditProfessionnelRepository.save(creditProfessionnel1);

        // Créer des remboursements
        Remboursement remboursement1 = new Remboursement();
        remboursement1.setCredit(creditPersonnel1);
        remboursement1.setDate(LocalDate.now());
        remboursement1.setMontant(BigDecimal.valueOf(300));
        remboursement1.setType(Remboursement.TypeRemboursement.MENSUALITE);
        remboursementRepository.save(remboursement1);
    }
} 