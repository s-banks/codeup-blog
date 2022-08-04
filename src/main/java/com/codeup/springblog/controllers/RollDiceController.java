package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {

	@GetMapping("/roll-dice")
	public String rollDice() {
		return "roll-dice";
	}

	@GetMapping("/roll-dice/{di}")
	@ResponseBody
	public String rollDice(@PathVariable int di) {
		int random = (int) (Math.random() * 6) + 1;
		return String.format("You rolled a %d. It was %s because the random number was %d", di, random == di ? "a success" : "a failure", random);
	}
}
