package dev.web.crm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.web.crm.entite.CollegueParticipant;
import dev.web.crm.persistence.CollegueParticipantRepository;

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

	
	}
	
	/*
	 public Optional<CollegueParticipant> rechercherParMail(String email) {
	        return collegueParticipantRepository.findById(email);
	    }
	    */
	
	 /*
	@Modifying
	@Query("update CollegueParticipant cp set cp.pictureUrl = ?")
	public String setCollegueParticipantById(String pictureUrl) {
		
        if (pictureUrl.("")) {
            cp.setPictureUrl(pictureUrl);
            return collegueParticipantRepository.save(cp);
        }
		
}
*/
