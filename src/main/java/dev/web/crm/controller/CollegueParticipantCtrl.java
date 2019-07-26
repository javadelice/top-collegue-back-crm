package dev.web.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.web.crm.dto.CollegueUser;
import dev.web.crm.dto.Picture;
import dev.web.crm.entite.CollegueParticipant;
import dev.web.crm.entite.CollegueScore;
import dev.web.crm.service.CollegueParticipantService;

@CrossOrigin
@RestController
public class CollegueParticipantCtrl {
    @Autowired
	private CollegueParticipantService collegueParticipantService;

    @Secured("ROLE_USER")
	@RequestMapping(method = RequestMethod.POST, path = "/registration")
	public ResponseEntity<?> finaliserInscription(@RequestBody Picture picture) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		collegueParticipantService.finaliserInscription(Optional.ofNullable(picture.getPictureUrl()), email);

        return ResponseEntity.ok().build();

    }

    @Secured("ROLE_USER")
	@RequestMapping(
            method = RequestMethod.GET,
            path = "/me")
    public CollegueUser recupCollegueFromEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CollegueUser collegue = collegueParticipantService.chercherParEmail(email);
        return collegue;
    }
    
    /*
    @Secured("ROLE_USER")
	@RequestMapping(
            method = RequestMethod.GET,
            path = "/result")
    /*public Optional<CollegueScore> recupererScoreCollegues(int score) {
		return collegueParticipantService.findByScore(score);
   
    }
    
    
    public List<CollegueScore> recupererScoreCollegues() {
    	List<CollegueParticipant> tousLesScoresCollegues = collegueParticipantService.lister();
    	
    	List<CollegueScore> resultats = new ArrayList<CollegueScore>();
    	
    	for (CollegueScore collegueScore : resultats) {
			resultats.add(new CollegueScore(collegueScore.getCollegue(), collegueScore.getScore()));
		}
    	
    	
        return resultats;
}
    */
}