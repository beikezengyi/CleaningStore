package com.cleaningstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cleaningstore.jdbc.bean.CustomerBean;
import com.cleaningstore.jdbc.bean.OrderBean;
import com.cleaningstore.jdbc.mapper.OrderMapper;
import com.cleaningstore.jdbc.mapper.StoreMapper;

@Controller
public class CreateOrderController {

	@Autowired
	StoreMapper storeMapper;

	@Autowired
	OrderMapper orderMapper;

	@GetMapping(value = "/createOrder")
	public String createOrder_get(Map<String, Object> model) {
		OrderBean orderBean = new OrderBean();
		model.put("orderBean", orderBean);
		model.put("storeList", storeMapper.selectStore());
		return "createOrder";
	}

	@PostMapping(value = "/createOrder")
	public String createOrder_post(Map<String, Object> model, // ) {
			@ModelAttribute OrderBean orderBean) {

		Integer id = orderBean.getCustomerNumber();
		if (id != null && id != 0) {
			orderBean.setCustomerNumber(id);
			orderMapper.insertOrder(orderBean);
			OrderBean inertedBean = orderMapper.selectMonestNew();
			model.put("inertedBean", inertedBean);
		} else {
			model.put("checkerror", true);
		}
		model.put("storeList", storeMapper.selectStore());
		model.put("orderBean", new OrderBean());
		model.put("customerBean", new CustomerBean());
		model.put("choosedCustomer", new CustomerBean());
		return "createOrder";
	}

	// @InitBinder
	// public void initBinder(WebDataBinder binder) {
	// CustomDateEditor editor = new CustomDateEditor(new
	// SimpleDateFormat("yyyy-MM-dd HH:mm"), true);
	// binder.registerCustomEditor(Date.class, editor);
	// }
}
