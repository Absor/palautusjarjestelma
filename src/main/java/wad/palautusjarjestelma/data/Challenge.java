package wad.palautusjarjestelma.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="challenges")
public class Challenge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @NotEmpty
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="publish_date")
    private Date publishDate = new Date();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="deadline_date")
    private Date deadlineDate = new Date();
    @Min(0)
    @Column(name="max_submissions")
    private int maxSubmissions;
    @OneToOne
    private SavedFile templateFile;
    @NotEmpty
    @Column(name="mq_address")
    private String messageQueueAddress;
    @NotEmpty
    @Column(name="mq_name")
    private String messageQueueName;
    @Transient
    private MultipartFile formTemplateFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
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
