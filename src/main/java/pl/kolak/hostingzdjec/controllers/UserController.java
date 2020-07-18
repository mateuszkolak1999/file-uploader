package pl.kolak.hostingzdjec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kolak.hostingzdjec.model.UserApp;
import pl.kolak.hostingzdjec.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user",new UserApp());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(name = "user") UserApp user, Model model){
        userService.addUser(user);
        model.addAttribute("message","Zarejestrowano!");
        return "register";
    }
}
