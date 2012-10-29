package wad.palautusjarjestelma.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wad.palautusjarjestelma.data.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("SELECT c FROM Challenge c WHERE c.publishDate < :date AND c.deadlineDate > :date")
    List<Challenge> findByDate(@Param("date") Date date);
}
