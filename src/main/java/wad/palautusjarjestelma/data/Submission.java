package wad.palautusjarjestelma.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "submissions")
public class Submission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="submission_number")
    private int submissionNumber;
    @OneToOne
    private Challenge challenge;
    @OneToOne
    private User user;
    @OneToOne
    private SavedFile submissionFile;
    @Transient
    private MultipartFile formSubmissionFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(int submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SavedFile getSubmissionFile() {
        return submissionFile;
    }

    public void setSubmissionFile(SavedFile submissionFile) {
        this.submissionFile = submissionFile;
    }

    public MultipartFile getFormSubmissionFile() {
        return formSubmissionFile;
    }

    public void setFormSubmissionFile(MultipartFile formSubmissionFile) {
        this.formSubmissionFile = formSubmissionFile;
    }
}
