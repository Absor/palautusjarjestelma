package wad.palautusjarjestelma.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.palautusjarjestelma.data.Challenge;
import wad.palautusjarjestelma.data.Submission;
import wad.palautusjarjestelma.data.User;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByChallenge(Challenge challenge);

    List<Submission> findByChallengeAndUser(Challenge challenge, User user);

    List<Submission> findByUser(User user);
}
