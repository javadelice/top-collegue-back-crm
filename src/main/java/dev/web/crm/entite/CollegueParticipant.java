package dev.web.crm.entite;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class CollegueParticipant {
    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String pictureUrl;
    @Value("${nb.vote.positive}")
    private int nbPlusRemainingVotes;
    @Value("${nb.vote.negative}")
    private int nbMinusRemainingVotes;
    @OneToMany(mappedBy = "collegueElector")
    private Set<Vote> votes;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('SUSCRIBED', 'SUSCRIBED_CONFIRMED','VOTE_CONFIRMED')")
    private StatusCollegue status;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    public CollegueParticipant(String email, String password, String firstName, String lastName, String pictureUrl, StatusCollegue status) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.status = status;
    }

    public CollegueParticipant(String email, String password, String firstName, String lastName, String pictureUrl, StatusCollegue status, List<String> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.status = status;
        this.roles = roles;
    }

    public StatusCollegue getStatus() {
        return status;
    }

    public CollegueParticipant() {
    }

    public void setStatus(StatusCollegue status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getNbPlusRemainingVotes() {
        return nbPlusRemainingVotes;
    }

    public void setNbPlusRemainingVotes(int nbPlusRemainingVotes) {
        this.nbPlusRemainingVotes = nbPlusRemainingVotes;
    }

    public int getNbMinusRemainingVotes() {
        return nbMinusRemainingVotes;
    }

    public void setNbMinusRemainingVotes(int nbMinusRemainingVotes) {
        this.nbMinusRemainingVotes = nbMinusRemainingVotes;
    }

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
