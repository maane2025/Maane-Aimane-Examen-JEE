package ma.maane.examjee.mapper;

import ma.maane.examjee.dto.*;
import ma.maane.examjee.entities.*;
import ma.maane.examjee.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    @Autowired
    private ClientRepository clientRepository;

    public CreditDTO toDTO(Credit credit) {
        if (credit == null) return null;

        if (credit instanceof CreditPersonnel creditPersonnel) {
            CreditPersonnelDTO dto = new CreditPersonnelDTO();
            mapBasicCreditProperties(credit, dto);
            dto.setMotif(creditPersonnel.getMotif());
            return dto;
        } else if (credit instanceof CreditImmobilier creditImmobilier) {
            CreditImmobilierDTO dto = new CreditImmobilierDTO();
            mapBasicCreditProperties(credit, dto);
            dto.setTypeBien(creditImmobilier.getTypeBien());
            return dto;
        } else if (credit instanceof CreditProfessionnel creditProfessionnel) {
            CreditProfessionnelDTO dto = new CreditProfessionnelDTO();
            mapBasicCreditProperties(credit, dto);
            dto.setMotif(creditProfessionnel.getMotif());
            dto.setRaisonSociale(creditProfessionnel.getRaisonSociale());
            return dto;
        }

        throw new IllegalArgumentException("Type de crédit non supporté");
    }

    public Credit toEntity(CreditDTO dto) {
        if (dto == null) return null;

        Credit credit;
        if (dto instanceof CreditPersonnelDTO creditPersonnelDTO) {
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            mapBasicCreditProperties(dto, creditPersonnel);
            creditPersonnel.setMotif(creditPersonnelDTO.getMotif());
            credit = creditPersonnel;
        } else if (dto instanceof CreditImmobilierDTO creditImmobilierDTO) {
            CreditImmobilier creditImmobilier = new CreditImmobilier();
            mapBasicCreditProperties(dto, creditImmobilier);
            creditImmobilier.setTypeBien(creditImmobilierDTO.getTypeBien());
            credit = creditImmobilier;
        } else if (dto instanceof CreditProfessionnelDTO creditProfessionnelDTO) {
            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            mapBasicCreditProperties(dto, creditProfessionnel);
            creditProfessionnel.setMotif(creditProfessionnelDTO.getMotif());
            creditProfessionnel.setRaisonSociale(creditProfessionnelDTO.getRaisonSociale());
            credit = creditProfessionnel;
        } else {
            throw new IllegalArgumentException("Type de DTO de crédit non supporté");
        }

        // Associer le client
        if (dto.getClientId() != null) {
            credit.setClient(clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client non trouvé")));
        }

        return credit;
    }

    private void mapBasicCreditProperties(Credit source, CreditDTO destination) {
        destination.setId(source.getId());
        destination.setDateDemande(source.getDateDemande());
        destination.setStatut(source.getStatut());
        destination.setDateAcceptation(source.getDateAcceptation());
        destination.setMontant(source.getMontant());
        destination.setDureeRemboursement(source.getDureeRemboursement());
        destination.setTauxInteret(source.getTauxInteret());
        destination.setClientId(source.getClient().getId());
    }

    private void mapBasicCreditProperties(CreditDTO source, Credit destination) {
        destination.setId(source.getId());
        destination.setDateDemande(source.getDateDemande());
        destination.setStatut(source.getStatut());
        destination.setDateAcceptation(source.getDateAcceptation());
        destination.setMontant(source.getMontant());
        destination.setDureeRemboursement(source.getDureeRemboursement());
        destination.setTauxInteret(source.getTauxInteret());
    }
} 