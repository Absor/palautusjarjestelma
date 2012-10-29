package wad.palautusjarjestelma.service;

import java.util.Collection;
import java.util.Date;
import wad.palautusjarjestelma.data.Challenge;

public interface ChallengeService extends ServiceInterface<Challenge> {
    
    public Collection<Challenge> findByDate(Date date);
}
