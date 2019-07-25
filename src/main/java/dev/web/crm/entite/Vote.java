package dev.web.crm.entite;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "elector")
    private CollegueParticipant collegueElector;
    @ManyToOne
    @JoinColumn(name = "elected")
    private CollegueParticipant collegueElected;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('POSITIVE', 'NEGATIVE')")
    private TypeVote typeVote;

    public Vote(CollegueParticipant collegueElector, CollegueParticipant collegueElected, TypeVote typeVote) {
        this.collegueElector = collegueElector;
        this.collegueElected = collegueElected;
        this.typeVote = typeVote;
    }

    public Vote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CollegueParticipant getCollegueElector() {
        return collegueElector;
    }

    public void setCollegueElector(CollegueParticipant collegueElector) {
        this.collegueElector = collegueElector;
    }

    public CollegueParticipant getCollegueElected() {
        return collegueElected;
    }

    public void setCollegueElected(CollegueParticipant collegueElected) {
        this.collegueElected = collegueElected;
    }

    public TypeVote getTypeVote() {
        return typeVote;
    }

    public void setTypeVote(TypeVote typeVote) {
        this.typeVote = typeVote;
    }
}
