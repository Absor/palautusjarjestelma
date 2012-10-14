package wad.palautusjarjestelma.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.palautusjarjestelma.data.Submission;
import wad.palautusjarjestelma.repository.SubmissionRepository;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    @Transactional(readOnly = false)
    public Submission create(Submission submission) {
        return submissionRepository.save(submission);
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
}
