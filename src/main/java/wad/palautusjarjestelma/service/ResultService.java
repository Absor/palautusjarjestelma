package wad.palautusjarjestelma.service;

import wad.palautusjarjestelma.data.Result;
import wad.palautusjarjestelma.data.Submission;

public interface ResultService extends ServiceInterface<Result> {

    Result findBySubmission(Submission submission);
}
