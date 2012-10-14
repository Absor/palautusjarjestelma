package wad.palautusjarjestelma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("users", userService.list());
        return "user-list";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String add(@ModelAttribute User user) {
        User savedUser = userService.add(user);
        return "redirect:/app/users/" + savedUser.getId();
    }
    
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public String view(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getById(userId));
        return "user-view";
    }
    
    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long userId) {
        userService.deleteById(userId);
        return "redirect:/app/users";
    }
}
