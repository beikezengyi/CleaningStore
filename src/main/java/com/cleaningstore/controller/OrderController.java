package com.cleaningstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cleaningstore.jdbc.OrderMapper;
import com.cleaningstore.web.bean.condition.SelectOrderCondition;
import com.cleaningstore.web.bean.result.SelectOrderResult;

@Controller
public class OrderController {

	 @Autowired
	 OrderMapper ordermapper;

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String selectOrder(Map<String, Object> model//) {
		, @RequestBody(required = false) SelectOrderCondition cond) {
		 List<SelectOrderResult> orderResultList // = new ArrayList<>();
		 = ordermapper.selectOrder(cond);
		 model.put("orderResultList", orderResultList);
		return "order";
	}

}
