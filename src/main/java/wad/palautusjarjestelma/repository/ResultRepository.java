package wad.palautusjarjestelma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.palautusjarjestelma.data.Result;
import wad.palautusjarjestelma.data.Submission;

public interface ResultRepository extends JpaRepository<Result, Long> {

    Result findBySubmission(Submission submission);
}
