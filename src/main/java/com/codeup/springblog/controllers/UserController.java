package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	private PasswordEncoder passwordEncoder;
	private UserRepository usersDao;

	public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
		this.usersDao = usersDao;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/sign-up")
	public String showSignUp(Model model) {
		model.addAttribute("newUser", new User());
		return "signup";
	}

	@PostMapping("/sign-up")
	public String saveUser(@ModelAttribute User user) {
		String hash = passwordEncoder.encode(user.getPassword());
		user.setPassword(hash);
		usersDao.save(user);
		return "redirect:/profile";
	}

	@GetMapping("/profile")
	public String showProfile() {
		//Access the logged in user from a controller:
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "profile";
	}

}
