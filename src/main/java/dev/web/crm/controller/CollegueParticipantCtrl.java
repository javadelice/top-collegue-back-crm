package dev.web.crm.controller;

import dev.web.crm.dto.CollegueUser;
import dev.web.crm.dto.Picture;
import dev.web.crm.service.CollegueParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CollegueParticipantCtrl {
    @Autowired
	private CollegueParticipantService collegueParticipantService;

	@RequestMapping(method = RequestMethod.PATCH, path = "/registration")

	public ResponseEntity<?> finaliserInscription(@RequestBody Picture picture) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (picture.getPictureUrl() != null)
        return collegueParticipantService.finaliserInscription(picture.getPictureUrl(), email);
		return ResponseEntity.ok().build();

    }


	@RequestMapping(
            method = RequestMethod.GET,
            path = "/me")
    public CollegueUser recupCollegueFromEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CollegueUser collegue = collegueParticipantService.chercherParEmail(email);
        return collegue;
    }
}
