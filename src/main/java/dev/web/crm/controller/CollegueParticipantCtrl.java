package dev.web.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.web.crm.dto.Picture;
import dev.web.crm.service.CollegueParticipantService;
@CrossOrigin
@RestController
public class CollegueParticipantCtrl {

	private CollegueParticipantService collegueParticipantService;
	
	@RequestMapping(method = RequestMethod.PATCH, path = "/registration")
    
	/*public CollegueParticipant finaliserInscription(@RequestBody CollegueParticipant pictureUrl) {

        CollegueParticipant cp = new CollegueParticipant();

        if (cp.getPictureUrl() != null)
            collegueParticipantService.finaliserInscription(pictureUrl);

        return cp;
    }
    */
	
	public ResponseEntity<?> finaliserInscription(@RequestBody Picture picture) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (picture.getPictureUrl() != null)
        return collegueParticipantService.finaliserInscription(picture.getPictureUrl(), email);
		return ResponseEntity.ok().build();

    }

	
}
