package dev.web.crm.persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.web.crm.entite.CollegueParticipant;
import dev.web.crm.entite.StatusCollegue;


@Component
public class StartupDataInit {

	
	@Autowired
    CollegueParticipantRepository collegueRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        
        collegueRepo.save(new CollegueParticipant("capitaine.haddock@herge.com", passwordEncoder.encode("password"), "Capitaine", "Haddock", "https://www.tintin.com/tintin/persos/haddock/haddock.jpg", StatusCollegue.SUSCRIBED_CONFIRMED));
        collegueRepo.save(new CollegueParticipant("professeur.tournesol@herge.com", passwordEncoder.encode("password"), "Professeur", "Tournesol", "https://www.tintin.com/tintin/persos/tournesol/tournesol.jpg", StatusCollegue.SUSCRIBED_CONFIRMED));
        collegueRepo.save(new CollegueParticipant("méchant.rastapoupoulos@herge.com", passwordEncoder.encode("password"), "Méchant", "Rastapoupoulos", "https://www.tintin.com/tintin/persos/rasta/rasta.jpg", StatusCollegue.SUSCRIBED_CONFIRMED));
    
}
}
