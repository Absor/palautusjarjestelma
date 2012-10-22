package wad.palautusjarjestelma.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Challenge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date publish;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date deadline;
    private int maxSubmissions;
    @OneToOne
    private SavedFile templateFile;
    private String messageQueueAddress;
    private String messageQueueName;
    @Transient
    private MultipartFile formTemplateFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublish() {
        return publish;
    }

    public void setPublish(Date publish) {
        this.publish = publish;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getMaxSubmissions() {
        return maxSubmissions;
    }

    public void setMaxSubmissions(int maxSubmissions) {
        this.maxSubmissions = maxSubmissions;
    }

    public SavedFile getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(SavedFile templateFile) {
        this.templateFile = templateFile;
    }

    public String getMessageQueueAddress() {
        return messageQueueAddress;
    }

    public void setMessageQueueAddress(String messageQueueAddress) {
        this.messageQueueAddress = messageQueueAddress;
    }

    public String getMessageQueueName() {
        return messageQueueName;
    }

    public void setMessageQueueName(String messageQueueName) {
        this.messageQueueName = messageQueueName;
    }

    public MultipartFile getFormTemplateFile() {
        return formTemplateFile;
    }

    public void setFormTemplateFile(MultipartFile formTemplateFile) {
        this.formTemplateFile = formTemplateFile;
    }
}
