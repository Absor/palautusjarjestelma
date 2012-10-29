
package wad.palautusjarjestelma.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.palautusjarjestelma.data.Result;
import wad.palautusjarjestelma.data.Submission;
import wad.palautusjarjestelma.repository.ResultRepository;

@Service
public class ResultServiceImpl implements ResultService {
    
    @Autowired
    private ResultRepository resultRepository;

    @Override
    @Transactional(readOnly = false)
    public Result create(Result result) {
        return resultRepository.save(result);
    }

    @Override
    @Transactional(readOnly = true)
    public Result findById(Long id) {
        return resultRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Result result) {
        resultRepository.save(result);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Result result) {
        resultRepository.delete(result);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        resultRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result findBySubmission(Submission submission) {
        return resultRepository.findBySubmission(submission);
    }

}
