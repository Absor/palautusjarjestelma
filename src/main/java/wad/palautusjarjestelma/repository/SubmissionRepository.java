
package wad.palautusjarjestelma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.palautusjarjestelma.data.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

}
