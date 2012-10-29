package wad.palautusjarjestelma.service;

import java.util.Collection;
import java.util.List;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.palautusjarjestelma.data.Challenge;
import wad.palautusjarjestelma.data.Submission;
import wad.palautusjarjestelma.data.User;
import wad.palautusjarjestelma.queue.MessageQueueSender;
import wad.palautusjarjestelma.repository.SubmissionRepository;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private MessageQueueSender messageQueueSender;

    @Override
    @Transactional(readOnly = false)
    public Submission create(Submission submission) {
        Submission savedSubmission = submissionRepository.save(submission);
        try {
            messageQueueSender.send(savedSubmission);
        } catch (JMSException ex) {
            submissionRepository.delete(savedSubmission);
            return null;
        }
        return savedSubmission;
    }

    @Override
    @Transactional(readOnly = true)
    public Submission findById(Long id) {
        return submissionRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Submission submission) {
        submissionRepository.save(submission);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Submission submission) {
        submissionRepository.delete(submission);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        submissionRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Submission> findAll() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> findByChallenge(Challenge challenge) {
        return submissionRepository.findByChallenge(challenge);
    }

    @Override
    public List<Submission> findByChallengeAndUser(Challenge challenge, User user) {
        return submissionRepository.findByChallengeAndUser(challenge, user);
    }

    @Override
    public List<Submission> findByUser(User user) {
        return submissionRepository.findByUser(user);
    }
}
