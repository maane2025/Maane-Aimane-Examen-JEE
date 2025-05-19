package ma.maane.examjee.services;

import ma.maane.examjee.dto.*;
import ma.maane.examjee.entities.*;
import ma.maane.examjee.mapper.*;
import ma.maane.examjee.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditPersonnelRepository creditPersonnelRepository;

    @Autowired
    private CreditImmobilierRepository creditImmobilierRepository;

    @Autowired
    private CreditProfessionnelRepository creditProfessionnelRepository;

    @Autowired
    private CreditMapper creditMapper;

    @Transactional
    public CreditDTO creerCreditPersonnel(CreditPersonnelDTO creditDTO) {
        // Validation du montant
        validerMontantCredit(creditDTO.getMontant());

        // Définir le statut initial
        creditDTO.setStatut(Credit.StatutCredit.EN_COURS);
        creditDTO.setDateDemande(LocalDate.now());

        // Convertir et sauvegarder
        CreditPersonnel credit = (CreditPersonnel) creditMapper.toEntity(creditDTO);
        return creditMapper.toDTO(creditPersonnelRepository.save(credit));
    }

    @Transactional
    public CreditDTO creerCreditImmobilier(CreditImmobilierDTO creditDTO) {
        // Validation du montant
        validerMontantCredit(creditDTO.getMontant());

        // Définir le statut initial
        creditDTO.setStatut(Credit.StatutCredit.EN_COURS);
        creditDTO.setDateDemande(LocalDate.now());

        // Convertir et sauvegarder
        CreditImmobilier credit = (CreditImmobilier) creditMapper.toEntity(creditDTO);
        return creditMapper.toDTO(creditImmobilierRepository.save(credit));
    }

    @Transactional
    public CreditDTO creerCreditProfessionnel(CreditProfessionnelDTO creditDTO) {
        // Validation du montant
        validerMontantCredit(creditDTO.getMontant());

        // Définir le statut initial
        creditDTO.setStatut(Credit.StatutCredit.EN_COURS);
        creditDTO.setDateDemande(LocalDate.now());

        // Convertir et sauvegarder
        CreditProfessionnel credit = (CreditProfessionnel) creditMapper.toEntity(creditDTO);
        return creditMapper.toDTO(creditProfessionnelRepository.save(credit));
    }

    @Transactional(readOnly = true)
    public List<CreditDTO> listerCreditsPourClient(Long clientId) {
        return creditRepository.findAll().stream()
            .filter(credit -> credit.getClient().getId().equals(clientId))
            .map(creditMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public CreditDTO accepterCredit(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
            .orElseThrow(() -> new IllegalArgumentException("Crédit non trouvé"));

        // Vérifier les conditions d'acceptation
        if (verifierConditionsAcceptation(credit)) {
            credit.setStatut(Credit.StatutCredit.ACCEPTE);
            credit.setDateAcceptation(LocalDate.now());
            return creditMapper.toDTO(creditRepository.save(credit));
        } else {
            credit.setStatut(Credit.StatutCredit.REJETE);
            return creditMapper.toDTO(creditRepository.save(credit));
        }
    }

    private void validerMontantCredit(BigDecimal montant) {
        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant du crédit doit être positif");
        }
    }

    private boolean verifierConditionsAcceptation(Credit credit) {
        // Exemple de règles d'acceptation simplifiées
        // Dans un vrai projet, ces règles seraient plus complexes
        return credit.getMontant().compareTo(BigDecimal.valueOf(1000)) >= 0 &&
               credit.getDureeRemboursement() >= 12 &&
               credit.getTauxInteret().compareTo(BigDecimal.valueOf(0.5)) >= 0;
    }

    @Transactional(readOnly = true)
    public List<CreditDTO> listerCreditParStatut(Credit.StatutCredit statut) {
        return creditRepository.findByStatut(statut).stream()
            .map(creditMapper::toDTO)
            .collect(Collectors.toList());
    }
} 