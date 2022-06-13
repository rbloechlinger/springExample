package com.romeone.springapp.controller;

import com.romeone.springapp.model.User;
import com.romeone.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register_form";
        }

        User newUser = userRepository.save(user);
        System.out.println(newUser);

        return "register_success";
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> removeEmployee (@PathVariable("id") long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            userRepository.delete(optUser.get());
            return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
}