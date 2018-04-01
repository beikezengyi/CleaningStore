package com.cleaningstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {

	@GetMapping(value="/header")
	public String welcome() {
		return "header";
	}
}
