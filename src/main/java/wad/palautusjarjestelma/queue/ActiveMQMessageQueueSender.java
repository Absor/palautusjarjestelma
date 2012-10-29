package wad.palautusjarjestelma.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;
import wad.palautusjarjestelma.data.Challenge;

@Component
public class ActiveMQMessageQueueSender implements MessageQueueSender {

    @Override
    public void send(wad.palautusjarjestelma.data.Submission submission) throws JMSException {
        Challenge challenge = submission.getChallenge();
        
        // avataan yhteys
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(challenge.getMessageQueueAddress());
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // luodaan sessio viestien lähettämiseen, ei käytetä transaktioita
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // haetaan viestijono
        Destination destination = session.createQueue(challenge.getMessageQueueName());

        // luodaan messageproducer-olio, jota käytetään viestien lähetykseen
        MessageProducer producer = session.createProducer(destination);

        // luodaan lähetettävä viesti
        wad.aichallenges.domain.Submission mqSubmission =
                new wad.aichallenges.domain.Submission(submission);
        ObjectMessage message = session.createObjectMessage(mqSubmission);

        // lähetetään viesti
        producer.send(message);

        // suljetaan yhteys
        connection.close();
    }
}
