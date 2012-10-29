package wad.palautusjarjestelma.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.palautusjarjestelma.data.Challenge;
import wad.palautusjarjestelma.repository.ChallengeRepository;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Override
    @Transactional(readOnly = false)
    public Challenge create(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    @Transactional(readOnly = true)
    public Challenge findById(Long id) {
        return challengeRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Challenge challenge) {
        challengeRepository.delete(challenge);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        challengeRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Challenge> findAll() {
        return challengeRepository.findAll();
    }

    @Override
    public List<Challenge> findByDate(Date date) {
        return challengeRepository.findByDate(date);
    }
}
