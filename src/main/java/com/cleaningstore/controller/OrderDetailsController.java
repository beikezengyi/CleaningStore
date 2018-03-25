package com.cleaningstore.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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
import com.cleaningstore.web.bean.result.FromBean;
import com.cleaningstore.web.bean.result.OrderDetailsResult;
import com.cleaningstore.web.bean.result.YesNoBean;

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
		if (detailsList == null) {
			detailsList = new ArrayList<>();
		}
		if (detailsList.isEmpty()) {
			model.put("defaultDataFlg", true);
		}
		createNewData(orderNumber, detailsList);
		FromBean fromBean = new FromBean();
		fromBean.setDetailsList(detailsList);
		model.put("fromBean", fromBean);
		List<ThingBean> thingList = thingMapper.selectThing();
		model.put("thingList", thingList);
		List<WashWayBean> washWayList = washWayMapper.selectWashWay();
		model.put("washWayList", washWayList);
		model.put("yesno", createYesNo());
		return "orderDetails";
	}

	@PostMapping(value = "/orderDetails")
	public String selectOrder_post(Map<String, Object> model// ) {
			, @ModelAttribute(name = "fromBean") FromBean fromBean) {

		List<OrderDetailsResult> detailsList = fromBean.getDetailsList();
		model.put("fromBean", fromBean);
		model.put("yesno", createYesNo());
		List<ThingBean> thingList = thingMapper.selectThing();
		model.put("thingList", thingList);
		List<WashWayBean> washWayList = washWayMapper.selectWashWay();
		model.put("washWayList", washWayList);
		return "orderDetails";
	}

	private List<YesNoBean> createYesNo() {
		YesNoBean bean1 = new YesNoBean();
		bean1.setFlg("t");
		bean1.setDes("是");
		YesNoBean bean2 = new YesNoBean();
		bean2.setFlg("f");
		bean2.setDes("否");
		List<YesNoBean> list = new ArrayList<>();
		list.add(bean1);
		list.add(bean2);
		return list;
	}

	private void createNewData(int orderNumber, List<OrderDetailsResult> detailsList) {
		int detailNumber;
		if (detailsList.isEmpty()) {
			detailNumber = 1;
		} else {
			detailNumber = detailsList.get(detailsList.size() - 1).getCleanThingDetailsNumber() + 1;
		}
		OrderDetailsResult defaultData1 = new OrderDetailsResult();
		defaultData1.setCleanThingNumber(orderNumber);
		defaultData1.setCleanThingDetailsNumber(detailNumber);
		defaultData1.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		defaultData1.setViewFlg(true);
		OrderDetailsResult defaultData2 = new OrderDetailsResult();
		BeanUtils.copyProperties(defaultData1, defaultData2);
		defaultData2.setCleanThingDetailsNumber(detailNumber + 1);
		detailsList.add(defaultData1);
		detailsList.add(defaultData2);
	}
}
