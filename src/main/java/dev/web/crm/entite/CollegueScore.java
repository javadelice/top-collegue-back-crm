package dev.web.crm.entite;

import javax.persistence.*;

@Entity
public class CollegueScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private CollegueParticipant collegue;
    private int score;

    public CollegueScore(CollegueParticipant collegue, int score) {
        this.collegue = collegue;
        this.score = score;
    }

    public CollegueScore() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CollegueParticipant getCollegue() {
        return collegue;
    }

    public void setCollegue(CollegueParticipant collegue) {
        this.collegue = collegue;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
