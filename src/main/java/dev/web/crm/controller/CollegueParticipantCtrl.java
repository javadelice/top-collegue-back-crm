package dev.web.crm.controller;
import dev.web.crm.dto.CollegueLight;
import dev.web.crm.dto.CollegueUser;
import dev.web.crm.dto.Picture;
import dev.web.crm.service.CollegueParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    
    @Secured("ROLE_USER")
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/participants"
            )
    
    public List<CollegueLight> getAllColleguesPhotos (){
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CollegueLight> collegues = collegueParticipantService.lister(email);
        return collegues;
    }
    
	    
    
}
