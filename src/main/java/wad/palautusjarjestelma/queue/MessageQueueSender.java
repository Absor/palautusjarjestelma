package wad.palautusjarjestelma.queue;

import javax.jms.JMSException;
import wad.palautusjarjestelma.data.Submission;

public interface MessageQueueSender {

    void send(Submission submission) throws JMSException;
}
