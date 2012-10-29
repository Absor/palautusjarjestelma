package wad.aichallenges.domain;

import java.io.Serializable;
import wad.palautusjarjestelma.data.SavedFile;

public class Submission implements Serializable {

    // each submission that is sent for processing should have a unique id
    private String submissionId;
    // submission contenttype, e.g. application/gzip or text/plain
    private String contentType;
    // actual file content
    private byte[] data;

    public Submission(wad.palautusjarjestelma.data.Submission submission) {
        SavedFile submissionFile = submission.getSubmissionFile();
        submissionId = submission.getId().toString();
        contentType = submissionFile.getContentType();
        data = submissionFile.getContent();
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
