package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AdminController(UserRepository userRepository, RoleRepository repository) {
        this.userRepository = userRepository;
        this.roleRepository = repository;
    }

    @PostMapping("/searchUser")
    public String getUserByEmail(@RequestParam String userEmail, RedirectAttributes redirectAttributes) {
        User foundUserByEmail = userRepository.findByEmail(userEmail);
        if (foundUserByEmail == null) {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/admin";
        }

        List<Role> roles = roleRepository.findAll();
        redirectAttributes.addFlashAttribute("roles", roles);
        redirectAttributes.addFlashAttribute("foundUser", foundUserByEmail);
        return "redirect:/admin";
    }

    @PostMapping("/changeRoles")
    public String changeRoles(@RequestParam List<Role> allRoles, @RequestParam User user, Model model){
        Set<Role> userRoles = user.getRoles();
        userRoles.clear();

        for (Role role : allRoles){
            userRoles.add(role);
        }

        userRepository.save(user);
        model.addAttribute("changed", true);
        return "admin";
    }

}