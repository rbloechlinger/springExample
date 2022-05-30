package com.romeone.springapp.controller;

import com.romeone.springapp.model.CustomUser;

import com.romeone.springapp.model.CustomUserRepository;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private CustomUserRepository userRepository;

    @GetMapping("/register")
    public String showForm(Model model) {
        CustomUser user = new CustomUser();
        model.addAttribute("user", user);

        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);

        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") CustomUser user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register_form";
        }

        CustomUser newUser = userRepository.save(user);
        System.out.println(newUser);

        return "register_success";
    }
}