package wad.palautusjarjestelma.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.palautusjarjestelma.data.User;
import wad.palautusjarjestelma.service.UserService;

@Controller
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String list(@ModelAttribute User user, Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute User user,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            return "user-list";
        }
        User savedUser = userService.create(user);
        return "redirect:/app/users/" + savedUser.getId();
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public String view(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "user-view";
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/app/users";
    }
}
