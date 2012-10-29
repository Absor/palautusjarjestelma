package wad.aichallenges.domain;

import java.io.Serializable;

public class SubmissionResult implements Serializable {

    // each submission has a unique id that is mapped to the submission
    private String submissionId;
    // status code for submission, e.g. in queue, processing, ok, failure 
    // -- note that the frontend may receive more than one result file from
    // the backend: frontend should update it's info use the latest for the 
    // most up to date information    
    private String statusCode;
    // may contain e.g. reasons for failure or submission specific 
    // additional information
    private String description;
    // in some cases, the score is a double
    private Double score;
    // and in some other cases, the score is a string
    private String scoreString;

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getScoreString() {
        return scoreString;
    }

    public void setScoreString(String scoreString) {
        this.scoreString = scoreString;
    }
    
    
}
