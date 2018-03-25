package com.cleaningstore.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new Greeting());
		return "greeting";
	}

	@PostMapping("/greeting")
	public String greetingSubmit(@ModelAttribute Greeting greeting) {
		return "result";
	}

	@GetMapping("/popup")
	public String popup() {
		return "sub";
	}

	@GetMapping("/sub2")
	public String sub2() {
		return "sub2";
	}

	@GetMapping("/DatePicker")
	public String DatePicker() {
		return "DatePicker";
	}

}
