package wad.palautusjarjestelma.controller;

import java.security.Principal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wad.palautusjarjestelma.data.Challenge;
import wad.palautusjarjestelma.data.SavedFile;
import wad.palautusjarjestelma.data.Submission;
import wad.palautusjarjestelma.data.User;
import wad.palautusjarjestelma.service.ChallengeService;
import wad.palautusjarjestelma.service.ResultService;
import wad.palautusjarjestelma.service.SavedFileService;
import wad.palautusjarjestelma.service.SubmissionService;
import wad.palautusjarjestelma.service.UserService;

@Controller
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private SavedFileService savedFileService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "submissions", method = RequestMethod.GET)
    public String list(@ModelAttribute Submission submission, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        // For teacher show all submissions, for student show all his/her submissions.
        if (user.getRole().equals("teacher")) {
            model.addAttribute("submissions", submissionService.findAll());
        } else if (user.getRole().equals("student")) {
            model.addAttribute("submissions",
                    submissionService.findByUser(user));
        }
        model.addAttribute("submissions", submissionService.findAll());
        return "submission-list";
    }

    @RequestMapping(value = "challenges/{challengeId}/submissions", method = RequestMethod.GET)
    public String challengePathList(@ModelAttribute Submission submission,
            @PathVariable Long challengeId, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Challenge challenge = challengeService.findById(challengeId);
        // For teacher show all submissions, for student show all his/her submissions.
        if (user.getRole().equals("teacher")) {
            model.addAttribute("submissions", submissionService.findByChallenge(challenge));
        } else if (user.getRole().equals("student")) {
            model.addAttribute("submissions",
                    submissionService.findByChallengeAndUser(challenge, user));
        }
        // Quite useless, should be done some other way. (for jsp)
        model.addAttribute("challengeId", challengeId);
        return "challenge-submission-list";
    }

    @RequestMapping(value = "challenges/{challengeId}/submissions", method = RequestMethod.POST)
    public String add(@ModelAttribute Submission submission,
            @PathVariable Long challengeId,
            Principal principal,
            BindingResult bindingResult) {
        // Check errors
        if (bindingResult.hasErrors()) {
            return "challenge-submission-list";
        }
        // Link sending user to submission
        User user = userService.findByUsername(principal.getName());
        submission.setUser(user);
        // Link submission to challenge
        Challenge challenge = challengeService.findById(challengeId);
        submission.setChallenge(challenge);
        // Check for max submission limit
        // User submissions for current challenge
        Collection<Submission> submissions = submissionService.findByChallengeAndUser(challenge, user);
        int submissionsByUser = submissions.size();
        if (submissionsByUser >= challenge.getMaxSubmissions()) {
            bindingResult.rejectValue("formSubmissionFile", "error", "You have already sent maximum number of submissions.");
            return "challenge-submission-list";
        } else {
            submission.setSubmissionNumber(submissionsByUser + 1);
        }
        // Check file contents
        MultipartFile formFile = submission.getFormSubmissionFile();
        if (formFile.isEmpty()) {
            bindingResult.rejectValue("formSubmissionFile", "error", "Submission file can not be empty.");
            return "challenge-submission-list";
        } else {
            SavedFile savedFile = savedFileService.create(formFile);
            // Returns null only if file couldnt be saved
            if (savedFile == null) {
                bindingResult.rejectValue("formSubmissionFile", "error", "Error handling the submission file.");
                return "challenge-submission-list";
            } else {
                submission.setSubmissionFile(savedFile);
            }
        }
        Submission savedSubmission = submissionService.create(submission);
        // Returns null if couldn't be sent to message queue
        if (savedSubmission == null) {
            // Delete the already created savedFile
            savedFileService.delete(submission.getSubmissionFile());
            bindingResult.rejectValue("formSubmissionFile", "error", "Could not send submission.");
            return "challenge-submission-list";
        }
        return "redirect:/app/challenges/" + challengeId + "/submissions/" + savedSubmission.getId();
    }

    @RequestMapping(value = "submissions/{submissionId}", method = RequestMethod.GET)
    public String view(@PathVariable Long submissionId, Model model) {
        Submission submission = submissionService.findById(submissionId);
        model.addAttribute("submission", submission);
        model.addAttribute("result", resultService.findBySubmission(submission));
        return "submission-view";
    }

    @RequestMapping(value = "challenges/{challengeId}/submissions/{submissionId}", method = RequestMethod.GET)
    public String challengePathView(@PathVariable Long submissionId, Model model) {
        Submission submission = submissionService.findById(submissionId);
        model.addAttribute("submission", submission);
        model.addAttribute("result", resultService.findBySubmission(submission));
        return "challenge-submission-view";
    }

    @RequestMapping(value = "submissions/{submissionId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long submissionId) {
        submissionService.delete(submissionId);
        return "redirect:/app/submissions";
    }

    @RequestMapping(value = "challenges/{challengeId}/submissions/{submissionId}", method = RequestMethod.DELETE)
    public String challengePathDelete(@PathVariable Long submissionId) {
        submissionService.delete(submissionId);
        return "redirect:/app/submissions";
    }
    
    @RequestMapping(value = "submissions/{submissionId}/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadTemplate(@PathVariable Long submissionId) {
        SavedFile templateFile = submissionService.findById(submissionId).getSubmissionFile();
        if (templateFile == null) {
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }
        templateFile = savedFileService.getFileContent(templateFile);
        byte[] data = templateFile.getContent();
        if (data == null) {
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(templateFile.getContentType()));
        headers.setContentLength(data.length);
        headers.setLastModified(templateFile.getTimeAdded().getTime());

        return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
    }
}
