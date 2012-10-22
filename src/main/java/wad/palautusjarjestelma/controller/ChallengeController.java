package wad.palautusjarjestelma.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import wad.palautusjarjestelma.service.ChallengeService;
import wad.palautusjarjestelma.service.SavedFileService;

@Controller
@RequestMapping(value = "challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private SavedFileService savedFileService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String list(@ModelAttribute Challenge challenge, Model model) {
        model.addAttribute("challenges", challengeService.findAll());
        return "challenge-list";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String add(@ModelAttribute Challenge challenge,
            BindingResult bindingResult, Model model) {
        MultipartFile formFile = challenge.getFormTemplateFile();
        if (!formFile.isEmpty()) {
            SavedFile savedFile = savedFileService.create(formFile);
            if (savedFile == null) {
                bindingResult.rejectValue("formTemplateFile", "error", "Error handling the template file.");
            } else {
                challenge.setTemplateFile(savedFile);
            }

        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("challenges", challengeService.findAll());
            return "challenge-list";
        }
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

    @RequestMapping(value = "{challengeId}/downloadTemplate", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadTemplate(@PathVariable Long challengeId) {
        SavedFile templateFile = challengeService.findById(challengeId).getTemplateFile();
        System.out.println(templateFile);
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
//        headers.setLastModified(templateFile.getTimestamp().getTime());

        return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
    }
}
