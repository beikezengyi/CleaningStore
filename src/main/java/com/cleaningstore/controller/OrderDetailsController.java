package com.cleaningstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cleaningstore.jdbc.bean.OrderDetailsBean;
import com.cleaningstore.jdbc.bean.ThingBean;
import com.cleaningstore.jdbc.bean.WashWayBean;
import com.cleaningstore.jdbc.mapper.OrderDetailsMapper;
import com.cleaningstore.jdbc.mapper.ThingMapper;
import com.cleaningstore.jdbc.mapper.WashWayMapper;
import com.cleaningstore.web.bean.result.OrderDetailsResult;

@Controller
public class OrderDetailsController {

	@Autowired
	OrderDetailsMapper orderDetailsMapper;
	@Autowired
	ThingMapper thingMapper;
	@Autowired
	WashWayMapper washWayMapper;

	@RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
	public String selectOrder_get(Map<String, Object> model, //
			@RequestParam(value = "ordernumber", required = true) int orderNumber) {
		List<OrderDetailsResult> detailsList = orderDetailsMapper.selectOrderDetails(orderNumber);
		model.put("detailsList", detailsList);
		List<ThingBean> thingList = thingMapper.selectThing();
		model.put("thingList", thingList);
		List<WashWayBean> washWayList = washWayMapper.selectWashWay();
		model.put("washWayList", washWayList);
		return "orderDetails";
	}

	@RequestMapping(value = "/orderDetails", method = RequestMethod.POST)
	public String selectOrder_post(Map<String, Object> model// ) {
			, @PathVariable(value = "ordernumber") int orderNumber//
			, @ModelAttribute List<OrderDetailsBean> detailsList) {

		return "orderDetails";
	}
}
