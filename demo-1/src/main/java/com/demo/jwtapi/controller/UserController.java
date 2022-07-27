package com.demo.jwtapi.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.jwtapi.entity.Users;
import com.demo.jwtapi.repo.UserRepository;

@Controller
public class UserController {

	private final UserRepository userRepo;

	public UserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/")
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.addObject("users", userRepo.findAll());
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping("/signup")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new Users());
		return "add-user";
	}

	@PostMapping("/adduser")
	public String addUser(@Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println("Has errors " + user);
			return "add-user";
		}

		userRepo.save(user);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Users user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		model.addAttribute("user", user);
		return "update-user";
	}

	@PostMapping("/update/{id}")
	public String updateForm(@PathVariable("id") Long id, @Valid @ModelAttribute("user") Users user,
			BindingResult result, Model model) {
		if (result.hasErrors())
			return "update-user";

		userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setId(id);
		userRepo.save(user);
		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepo.deleteById(id);

		return "redirect:/";
	}

}
