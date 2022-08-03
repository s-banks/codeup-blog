package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {
		@GetMapping("/roll-dice")
		public String newInfoForm () {
			return "roll-dice";
		}

		@PostMapping("/roll-dice")
		public String newInfoSubmission(@RequestParam(name="info") String info, Model model) {
			model.addAttribute("info", String.format("Did you hear? %s.", info));
			return "roll-dice";
		}
	}

