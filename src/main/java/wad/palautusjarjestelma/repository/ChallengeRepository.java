
package wad.palautusjarjestelma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.palautusjarjestelma.data.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    
}
