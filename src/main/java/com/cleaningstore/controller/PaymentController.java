package com.cleaningstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cleaningstore.jdbc.bean.PaymentBean;

@Controller
public class PaymentController {

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment(PaymentBean py) {
		
		return "payment";
	}
}
