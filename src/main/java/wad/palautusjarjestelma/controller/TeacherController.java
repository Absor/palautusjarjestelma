package wad.palautusjarjestelma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.palautusjarjestelma.data.Teacher;

@Controller
@RequestMapping(value = "teachers")
public class TeacherController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String view(@ModelAttribute(value = "teacher") Teacher teacher) {
        return "teacher";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String add(@ModelAttribute Teacher teacher) {
        return "redirect:teacher";
    }
}
