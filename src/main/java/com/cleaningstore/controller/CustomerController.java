package com.cleaningstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cleaningstore.jdbc.bean.CustomerBean;
import com.cleaningstore.jdbc.bean.OrderBean;
import com.cleaningstore.jdbc.mapper.CustomerMapper;
import com.cleaningstore.jdbc.mapper.StoreMapper;
import com.cleaningstore.web.bean.condition.SelectCustomerCondition;

@Controller
public class CustomerController {

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	StoreMapper storeMapper;

	@GetMapping(value = "/allCustomer")
	public String selectCustomer_get(Map<String, Object> model) {
		model.put("cond", new SelectCustomerCondition());
		return "allCustomer";
	}

	@PostMapping(value = "/allCustomer")
	public String selectCustomer_post(Map<String, Object> model, //
			@ModelAttribute SelectCustomerCondition cond) {
		List<CustomerBean> allCustomer = customerMapper.selectCustomerByCon(cond.getName(), cond.getNumber());
		model.put("allCustomer", allCustomer);
		model.put("cond", cond);
		return "allCustomer";
	}

	@GetMapping(value = "/chooseCustomer/{id}")
	public String chooseCustomer(Map<String, Object> model, //
			@PathVariable(value = "id") int id) {

		CustomerBean cond = customerMapper.selectOne(id);
		model.put("choosedCustomer", cond);
		model.put("orderBean", new OrderBean());
		model.put("customerBean", new CustomerBean());
		model.put("storeList", storeMapper.selectStore());
		return "createOrder";
	}

}