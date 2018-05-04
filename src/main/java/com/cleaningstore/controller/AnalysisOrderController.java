package com.cleaningstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cleaningstore.jdbc.mapper.AnalysisOrderMapper;
import com.cleaningstore.web.bean.condition.AnalysisOrderCondition;
import com.cleaningstore.web.bean.result.AnalysisOrderResult;

@Controller
public class AnalysisOrderController {

	@Autowired
	AnalysisOrderMapper analysisOrderMapper;

	@GetMapping(value = "/analysisOrder")
	public String analysisOrder_g(Model model) {
		AnalysisOrderCondition cond = new AnalysisOrderCondition();
		model.addAttribute("cond", cond);
		model.addAttribute("successflg", false);
		return "analysisOrder";
	}

	@PostMapping(value = "/analysisOrder")
	public String analysisOrder_p(Model model, //
			@ModelAttribute AnalysisOrderCondition cond) {
		List<AnalysisOrderResult> aoList = analysisOrderMapper.selectAnalysisOrderByCon(cond);

		model.addAttribute("successflg", true);
		model.addAttribute("cond", cond);
		model.addAttribute("aoList", aoList);
		return "analysisOrder";
	}
}
