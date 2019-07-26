package dev.web.crm.service;

import dev.web.crm.dto.CollegueUser;
import dev.web.crm.entite.CollegueParticipant;
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
	public ResponseEntity<?> finaliserInscription(String picture, String email) {
		Optional<CollegueParticipant> cp = collegueParticipantRepository.findByEmail(email);
         return cp.map(collegue-> {
        	  collegue.setPictureUrl(picture);
        	  collegueParticipantRepository.save(collegue);
        	  return ResponseEntity.status(HttpStatus.CREATED).build();
          }).orElseGet(() -> {
        	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
          });

        }

	public CollegueUser chercherParEmail(String email) {
        return collegueParticipantRepository.findByEmail(email)
                .map(c -> new CollegueUser(c.getFirstName(), c.getLastName(), c.getStatus().toString()))
                .orElseThrow(() -> new CollegueNonTrouveException());
    }


}
