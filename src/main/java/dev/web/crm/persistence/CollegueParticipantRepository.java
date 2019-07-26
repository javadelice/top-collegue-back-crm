package dev.web.crm.persistence;

import dev.web.crm.entite.CollegueParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CollegueParticipantRepository extends JpaRepository<CollegueParticipant,String> {

    public Optional<CollegueParticipant> findByEmail(String email);
}
