package dev.web.crm.persistence;


import dev.web.crm.entite.CollegueParticipant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegueParticipantRepository extends JpaRepository<CollegueParticipant,String> {
	
	Optional<CollegueParticipant> findByEmail(String email);
	

}
