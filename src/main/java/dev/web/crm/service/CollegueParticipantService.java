package dev.web.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.web.crm.dto.CollegueUser;
import dev.web.crm.entite.CollegueParticipant;
import dev.web.crm.entite.StatusCollegue;
import dev.web.crm.exception.CollegueNonTrouveException;
import dev.web.crm.persistence.CollegueParticipantRepository;

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
    
    /*
    public List<CollegueParticipant> lister() {
		return collegueParticipantRepository.findAll();
	}
	*/

    /*
    public Optional<CollegueScore> findByScore(int score) {
    	return collegueParticipantRepository.findByScoreOrderByScoreDesc(score);
    }
*/

}
