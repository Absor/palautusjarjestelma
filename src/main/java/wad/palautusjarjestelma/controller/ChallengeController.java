package wad.palautusjarjestelma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.palautusjarjestelma.data.Challenge;
import wad.palautusjarjestelma.service.ChallengeService;

@Controller
@RequestMapping(value = "challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;
    
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String list(@ModelAttribute Challenge challenge, Model model) {
        model.addAttribute("challenges", challengeService.findAll());
        return "challenge-list";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String add(@ModelAttribute Challenge challenge) {
        Challenge savedChallenge = challengeService.create(challenge);
        return "redirect:/app/challenges/" + savedChallenge.getId();
    }
    
    @RequestMapping(value = "{challengeId}", method = RequestMethod.GET)
    public String view(@PathVariable Long challengeId, Model model) {
        model.addAttribute("challenge", challengeService.findById(challengeId));
        return "challenge-view";
    }
    
    @RequestMapping(value = "{challengeId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long challengeId) {
        challengeService.delete(challengeId);
        return "redirect:/app/challenges";
    }
}
