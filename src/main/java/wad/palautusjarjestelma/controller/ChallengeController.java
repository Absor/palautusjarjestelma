package wad.palautusjarjestelma.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String list(@ModelAttribute Challenge challenge, Model model) {
        model.addAttribute("allChallenges", challengeService.findAll());
        model.addAttribute("currentChallenges", challengeService.findByDate(new Date()));
        return "challenge-list";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute Challenge challenge,
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
