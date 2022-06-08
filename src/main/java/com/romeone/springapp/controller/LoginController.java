package com.romeone.springapp.controller;

import com.romeone.springapp.config.SecurityConfiguration;
import com.romeone.springapp.model.User;
import com.romeone.springapp.payload.request.LoginRequest;
import com.romeone.springapp.repository.RoleRepository;
import com.romeone.springapp.repository.UserRepository;
import com.romeone.springapp.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

	@PostMapping("/login")
	public String submitForm(LoginRequest loginRequest) {
		log.error("arrived");


		return "blub";
	}
}
