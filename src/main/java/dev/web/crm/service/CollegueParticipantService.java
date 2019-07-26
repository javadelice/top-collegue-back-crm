package dev.web.crm.service;

import dev.web.crm.dto.CollegueUser;
import dev.web.crm.entite.CollegueParticipant;
import dev.web.crm.entite.StatusCollegue;
import dev.web.crm.exception.CollegueNonTrouveException;
import dev.web.crm.persistence.CollegueParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollegueParticipantService {


    public CollegueParticipantService() {
        // TODO Auto-generated constructor stub
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



}
