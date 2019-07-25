package dev.web.crm.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.web.crm.dto.Picture;
import dev.web.crm.entite.CollegueParticipant;
import dev.web.crm.service.CollegueParticipantService;

@RestController
@RequestMapping(path = "/registration")
public class CollegueParticipantCtrl {

	private CollegueParticipantService collegueParticipantService;
	
	@RequestMapping(method = RequestMethod.PATCH)
    public Picture finaliserInscription(@RequestBody Picture pictureUrl) {

        Picture p = new Picture();

        if (p.getPictureUrl() != null)
            collegueParticipantService.finaliserInscription(pictureUrl);

        return p;
    }

	
}
