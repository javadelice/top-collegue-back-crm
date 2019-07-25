package dev.web.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.web.crm.dto.Picture;
import dev.web.crm.persistence.CollegueParticipantRepository;

@Service
public class CollegueParticipantService {
	
	
	public CollegueParticipantService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private CollegueParticipantRepository collegueParticipantRepository;
	
	public Picture finaliserInscription(Picture pictureUrl) {
		
            return collegueParticipantRepository.save(pictureUrl);
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
