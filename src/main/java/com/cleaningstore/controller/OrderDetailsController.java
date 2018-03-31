package com.cleaningstore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cleaningstore.jdbc.bean.ThingBean;
import com.cleaningstore.jdbc.bean.WashWayBean;
import com.cleaningstore.jdbc.mapper.OrderDetailsMapper;
import com.cleaningstore.jdbc.mapper.ThingMapper;
import com.cleaningstore.jdbc.mapper.WashWayMapper;
import com.cleaningstore.service.OrderDetailsService;
import com.cleaningstore.web.bean.result.FromBean;
import com.cleaningstore.web.bean.result.OrderDetailsResult;

@Controller
public class OrderDetailsController {

	@Autowired
	OrderDetailsMapper orderDetailsMapper;
	@Autowired
	ThingMapper thingMapper;
	@Autowired
	WashWayMapper washWayMapper;
	@Autowired
	OrderDetailsService service;

	@RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
	public String selectOrder_get(Map<String, Object> model, //
			@RequestParam(value = "ordernumber", required = true) int orderNumber) {

		detailsCommon(model, orderNumber);
		return "orderDetails";
	}

	@PostMapping(value = "/orderDetails")
	public String selectOrder_post(Map<String, Object> model// ) {
			, @ModelAttribute(name = "fromBean") FromBean fromBean) {

		List<OrderDetailsResult> detailsList = fromBean.getDetailsList();
		int countin = 0;
		int countup = 0;
		if (service.checkResult(detailsList)) {
			// DB提交
			for (OrderDetailsResult eachDe : detailsList) {
				if (eachDe.isCreated()) {
					orderDetailsMapper.updateOrderDetails(eachDe);
					countup++;
				} else if (eachDe.isToinsert()) {
					orderDetailsMapper.insertOrderDetails(eachDe);
					countin++;
				} else {
					// no process
				}
			}
			model.put("updateSuccess", true);
			model.put("countin", countin);
			model.put("countup", countup);
		} else {
			model.put("updateSuccess", false);
		}
		detailsCommon(model, fromBean.getOrderNumber());
		return "orderDetails";
	}

	private void detailsCommon(Map<String, Object> model, int orderNumber) {
		List<OrderDetailsResult> detailsList = orderDetailsMapper.selectOrderDetails(orderNumber);
		if (detailsList == null) {
			detailsList = new ArrayList<>();
		}
		if (detailsList.isEmpty()) {
			model.put("defaultDataFlg", true);
		}
		service.createNewData(orderNumber, detailsList);
		FromBean fromBean = new FromBean();
		fromBean.setOrderNumber(orderNumber);
		fromBean.setDetailsList(detailsList);
		model.put("fromBean", fromBean);
		List<ThingBean> thingList = thingMapper.selectThing();
		model.put("thingList", thingList);
		List<WashWayBean> washWayList = washWayMapper.selectWashWay();
		model.put("washWayList", washWayList);
		model.put("yesno", service.createYesNo());
	}

}
