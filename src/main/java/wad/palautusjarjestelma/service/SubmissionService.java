package wad.palautusjarjestelma.service;

import java.util.Collection;
import wad.palautusjarjestelma.data.Challenge;
import wad.palautusjarjestelma.data.Submission;
import wad.palautusjarjestelma.data.User;

public interface SubmissionService extends ServiceInterface<Submission> {

    Collection<Submission> findByChallenge(Challenge challenge);

    Collection<Submission> findByChallengeAndUser(Challenge challenge, User user);

    Collection<Submission> findByUser(User user);
}
