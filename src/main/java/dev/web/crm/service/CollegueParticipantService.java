package dev.web.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import dev.web.crm.dto.CollegueUser;
import dev.web.crm.exception.CollegueNonTrouveException;
import dev.web.crm.persistence.CollegueParticipantRepository;



@Repository
public class CollegueParticipantService {

	
	@Autowired
    private CollegueParticipantRepository collegueParticipantRepository;

	public CollegueParticipantService() {
		super();
	}
	
	public CollegueUser chercherParEmail(String email) {
        return collegueParticipantRepository.findByEmail(email)
                .map(c -> new CollegueUser(c.getFirstName(), c.getLastName(), c.getStatus().toString()))
                .orElseThrow(() -> new CollegueNonTrouveException());
    }
	
	
}
