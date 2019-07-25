package dev.web.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.web.crm.dto.CollegueUser;
import dev.web.crm.service.CollegueParticipantService;



@CrossOrigin (allowCredentials = "true")
@RestController

public class CollegueParticipantCtrl {

	
	 @Autowired
	    private CollegueParticipantService collegueParticipantService;
	 
	
	@RequestMapping(
            method = RequestMethod.GET, 
            path = "/me")
    public CollegueUser recupCollegueFromEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CollegueUser collegue = collegueParticipantService.chercherParEmail(email);
        return collegue;
    }
}
