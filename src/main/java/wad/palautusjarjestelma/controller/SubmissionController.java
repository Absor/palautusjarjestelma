package wad.palautusjarjestelma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.palautusjarjestelma.data.Submission;
import wad.palautusjarjestelma.service.SubmissionService;

@Controller
@RequestMapping(value = "submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String list(@ModelAttribute Submission submission, Model model) {
        model.addAttribute("submissions", submissionService.findAll());
        return "submission-list";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String add(@ModelAttribute Submission submission) {
        Submission savedSubmission = submissionService.create(submission);
        return "redirect:/app/submissions/" + savedSubmission.getId();
    }

    @RequestMapping(value = "{submissionId}", method = RequestMethod.GET)
    public String view(@PathVariable Long submissionId, Model model) {
        model.addAttribute("submission", submissionService.findById(submissionId));
        return "submission-view";
    }

    @RequestMapping(value = "{submissionId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long submissionId) {
        submissionService.delete(submissionId);
        return "redirect:/app/submissions";
    }
}
