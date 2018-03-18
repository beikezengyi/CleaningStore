package com.cleaningstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cleaningstore.jdbc.OrderMapper;
import com.cleaningstore.jdbc.StoreMapper;
import com.cleaningstore.jdbc.bean.OrderBean;

@Controller
public class CreateOrderController {

	@Autowired
	StoreMapper storeMapper;

	@Autowired
	OrderMapper orderMapper;

	@RequestMapping(value = "/createOrder", method = RequestMethod.GET)
	public String createOrder_get(Map<String, Object> model) {
		OrderBean orderBean = new OrderBean();
		model.put("orderBean", orderBean);
		model.put("storeList", storeMapper.selectStore());
		return "createOrder";
	}

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String createOrder_post(Map<String, Object> model// ) {
			, @ModelAttribute OrderBean orderBean) {

		orderMapper.insertOrder(orderBean);
		model.put("storeList", storeMapper.selectStore());
		model.put("orderBean", new OrderBean());
		OrderBean inertedBean = orderMapper.selectMonestNew();
		model.put("inertedBean", inertedBean);
		return "createOrder";
	}

	// @InitBinder
	// public void initBinder(WebDataBinder binder) {
	// CustomDateEditor editor = new CustomDateEditor(new
	// SimpleDateFormat("yyyy-MM-dd HH:mm"), true);
	// binder.registerCustomEditor(Date.class, editor);
	// }
}
