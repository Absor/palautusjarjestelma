package wad.palautusjarjestelma.example;

import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import wad.aichallenges.domain.Submission;
import wad.aichallenges.domain.SubmissionResult;

@Component
public class ExampleMessageProcessor implements MessageListener {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void onMessage(Message msg) {
        // Message receive
        final ObjectMessage message = (ObjectMessage) msg;
        
        // Do stuff

        // send back.
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                SubmissionResult submissionResult = new SubmissionResult();
                submissionResult.setDescription(UUID.randomUUID().toString());
                submissionResult.setScoreString(UUID.randomUUID().toString());
                submissionResult.setStatusCode(UUID.randomUUID().toString());
                Submission submission = (Submission) message.getObject();
                submissionResult.setSubmissionId(submission.getSubmissionId());
                return session.createObjectMessage(submissionResult);
            }
        });
    }
}
