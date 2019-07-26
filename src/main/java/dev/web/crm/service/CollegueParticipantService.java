package dev.web.crm.service;
import dev.web.crm.dto.CollegueLight;
import dev.web.crm.dto.CollegueUser;
import dev.web.crm.entite.CollegueParticipant;
import dev.web.crm.entite.StatusCollegue;
import dev.web.crm.exception.CollegueNonTrouveException;
import dev.web.crm.exception.NbColleguesInsuffisantsException;
import dev.web.crm.persistence.CollegueParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CollegueParticipantService {


	private static final int NB_PARTICIPANTS_MINIMUM = 2;
	
    public CollegueParticipantService() {
    }

    @Autowired
    private CollegueParticipantRepository collegueParticipantRepository;

    //public CollegueParticipant finaliserInscription(CollegueParticipant pictureUrl) {
    public CollegueParticipant finaliserInscription(Optional<String> picture, String email) {
        return collegueParticipantRepository.findByEmail(email)
                .map(collegue -> {
                    picture.ifPresent(collegue::setPictureUrl);
                    collegue.setStatus(StatusCollegue.SUSCRIBED_CONFIRMED);
                    collegueParticipantRepository.save(collegue);
                    return collegue;
                }).orElseThrow(CollegueNonTrouveException::new);


    }

    public CollegueUser chercherParEmail(String email) {
        return collegueParticipantRepository.findByEmail(email)
                .map(c -> new CollegueUser(c.getFirstName(), c.getLastName(), c.getStatus().toString()))
                .orElseThrow(CollegueNonTrouveException::new);
    }
    
    public List<CollegueLight> lister(String email) {
    	if (collegueParticipantRepository.findAll().size() < NB_PARTICIPANTS_MINIMUM) {
    		throw new NbColleguesInsuffisantsException("Attente de nouveaux participants");
    	}
    	
        return collegueParticipantRepository.findAll()
                .stream()
                .filter(c -> !c.getEmail().equals(email))
                .map(c -> new CollegueLight(c.getFirstName(), c.getLastName(), c.getPictureUrl()))
                .collect(Collectors.toList());
    }



}
