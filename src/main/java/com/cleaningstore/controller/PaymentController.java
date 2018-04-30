package com.cleaningstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cleaningstore.jdbc.mapper.PaymentMapper;
import com.cleaningstore.web.bean.condition.SelectPaymentCondition;
import com.cleaningstore.web.bean.result.PaymentResult;

@Controller
public class PaymentController {

	@Autowired
	PaymentMapper paymentMapper;

	@GetMapping(value = "/payment")
	public String payment_g(Model model) {
		model.addAttribute("searchPayment", new SelectPaymentCondition());
		model.addAttribute("successflg", false);
		return "payment";
	}
	
	@PostMapping(value = "/payment")
	public String payment_p(Model model,//
			@ModelAttribute SelectPaymentCondition searchPayment) {
		
		List<PaymentResult> pylist=paymentMapper.selectPayment(searchPayment);
		model.addAttribute("searchPayment", searchPayment);
		model.addAttribute("successflg", true);
		model.addAttribute("pylist", pylist);
		return "payment";
	}	
}
