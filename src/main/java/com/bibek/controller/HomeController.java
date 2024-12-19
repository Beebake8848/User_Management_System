package com.bibek.controller;

import com.bibek.UsermanagementApplication;
import com.bibek.model.UserDtls;
import com.bibek.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDtls user, RedirectAttributes redirectAttributes) {

        boolean f = userService.checkEmail(user.getEmail());
        if (f) {
            redirectAttributes.addFlashAttribute("msg", "Email already existed");
        } else {
            UserDtls userDtls = userService.createUser(user);
            if (userDtls != null) {
                redirectAttributes.addFlashAttribute("msg", "Registration Successful");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Error");
            }
        }
        return "redirect:/register";
    }
}
