package com.romeone.springapp.controller;

import com.romeone.springapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String showForm(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "login";
    }
}
