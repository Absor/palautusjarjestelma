package wad.palautusjarjestelma.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wad.palautusjarjestelma.data.Result;
import wad.palautusjarjestelma.service.ResultService;

@Controller
@RequestMapping(value="results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Collection<Result> listAll(@ModelAttribute Result result, Model model) {
        return resultService.findAll();
    }

    @RequestMapping(value = "{resultId}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Result viewByResultId(@PathVariable Long resultId, Model model) {
        return resultService.findById(resultId);
    }
}
