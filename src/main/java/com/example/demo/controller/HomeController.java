package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {

    private RegistrationService registrationService;

    @Autowired
    public HomeController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String homeSite() {
        return "home";
    }

    @GetMapping("/admin")
    public String getAdminSite() {
        return "admin";
    }

    @GetMapping("/moderator")
    public String getModeratorSite() {
        return "moderator";
    }

    @GetMapping("/user")
    public String getUserSite() {
        return "user";
    }

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registrationForm";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute User user, RedirectAttributes redirectAttributes) {

//        if (bindingResult.hasErrors()) {
//            return "registrationError";
//        }
        registrationService.registerNewUser(user);

        redirectAttributes.addFlashAttribute("registration", true);
        return "redirect:/";
    }
}
