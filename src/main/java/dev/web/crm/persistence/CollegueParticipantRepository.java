package dev.web.crm.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.web.crm.dto.Picture;
import dev.web.crm.entite.CollegueParticipant;

@Repository
public interface CollegueParticipantRepository extends JpaRepository<CollegueParticipant,String> {

	Optional<CollegueParticipant> findByEmail(String email);

	Picture save(Picture pictureUrl);

}
