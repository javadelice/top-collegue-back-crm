package dev.web.crm.entite;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class CollegueParticipant {
    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String pictureUrl;
    private int nbPlusRemainingVotes;
    private int nbMinusRemainingVotes;
    @OneToMany(mappedBy = "collegueElector")
    private Set<Vote> votes;

    public CollegueParticipant(String email, String password, String firstName, String lastName, String pictureUrl) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
    }

    public CollegueParticipant() {
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
}
