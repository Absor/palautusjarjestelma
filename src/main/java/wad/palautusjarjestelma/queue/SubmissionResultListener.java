package wad.palautusjarjestelma.queue;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wad.aichallenges.domain.SubmissionResult;
import wad.palautusjarjestelma.data.Result;
import wad.palautusjarjestelma.service.ResultService;
import wad.palautusjarjestelma.service.SubmissionService;

@Component
public class SubmissionResultListener implements MessageListener {
    
    @Autowired
    private ResultService resultService;
    @Autowired
    private SubmissionService submissionService;

    @Override
    public void onMessage(Message msg) {
        ObjectMessage message = (ObjectMessage) msg;
        try {
            SubmissionResult submissionResult = (SubmissionResult) message.getObject();
            Result result = new Result();
            result.setDescription(submissionResult.getDescription());
            result.setScore(submissionResult.getScore());
            result.setScoreString(submissionResult.getScoreString());
            result.setStatusCode(submissionResult.getStatusCode());
            Long submissionId = Long.parseLong(submissionResult.getSubmissionId());
            result.setSubmission(submissionService.findById(submissionId));
            resultService.create(result);
        } catch (JMSException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
