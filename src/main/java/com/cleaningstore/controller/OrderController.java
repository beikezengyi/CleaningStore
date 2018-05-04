package com.cleaningstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cleaningstore.jdbc.mapper.OrderMapper;
import com.cleaningstore.web.bean.condition.SelectOrderCondition;
import com.cleaningstore.web.bean.result.SelectOrderResult;

@Controller
public class OrderController {

	@Autowired
	OrderMapper ordermapper;

	@GetMapping(value = "/order")
	public String selectOrder_get(Map<String, Object> model) {
		List<SelectOrderResult> orderResultList = ordermapper.selectOrder();
		model.put("orderResultList", orderResultList);
		SelectOrderCondition cond = new SelectOrderCondition();
		model.put("cond", cond);
		return "order";
	}

	@PostMapping(value = "/order")
	public String selectOrder_post(Map<String, Object> model// ) {
			, @ModelAttribute SelectOrderCondition cond) {
		List<SelectOrderResult> orderResultList = ordermapper.selectOrderByCond(cond);
		model.put("orderResultList", orderResultList);
		model.put("cond", cond);
		return "order";
	}

	// @InitBinder
	// public void initBinder(WebDataBinder binder) {
	// CustomDateEditor editor = new CustomDateEditor(new
	// SimpleDateFormat("yyyy-MM-dd"), true);
	// binder.registerCustomEditor(Date.class, editor);
	// }
}
