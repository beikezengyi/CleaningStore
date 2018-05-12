package com.cleaningstore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cleaningstore.jdbc.bean.CustomerBean;
import com.cleaningstore.jdbc.bean.OrderBean;
import com.cleaningstore.jdbc.bean.PaymentBean;
import com.cleaningstore.jdbc.mapper.CustomerMapper;
import com.cleaningstore.jdbc.mapper.PaymentMapper;
import com.cleaningstore.jdbc.mapper.StoreMapper;
import com.cleaningstore.service.CustomerService;
import com.cleaningstore.web.bean.condition.SelectCustomerCondition;

@Controller
public class CustomerController {

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	StoreMapper storeMapper;

	@Autowired
	PaymentMapper paymentMapper;

	@Autowired
	CustomerService service;

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

	@GetMapping(value = "/chooseCustomer/{name}")
	public String chooseCustomer(Map<String, Object> model, //
			@PathVariable(value = "name") String customerName) {
		OrderBean orderBean = new OrderBean();
		orderBean.setCustomerName(customerName);
		model.put("orderBean", orderBean);
		model.put("storeList", storeMapper.selectStore());
		return "createOrder";
	}

	@GetMapping(value = "/createCustomer")
	public String createCustomer_g(Map<String, Object> model) {

		model.put("cu", new CustomerBean());
		return "createCustomer";
	}

	@PostMapping(value = "/createCustomer")
	public String createCustomer_p(Map<String, Object> model, //
			@ModelAttribute(name = "cu") CustomerBean cu) {

		if (service.checkCustomer(cu)) {
			// insert
			Integer giveAmount = 0;
			try {
				giveAmount = cu.getAfterCharge() - cu.getAccountPayment();
			} catch (NullPointerException e) {
				giveAmount = 0;
			}
			CustomerBean insertedCustomer = customerMapper.insertCustomer(cu);
			// CustomerBean insertedCustomer =
			// customerMapper.selectInserted(cu.getCustomerName());
			// payment
			if (cu.getAccountPayment() != null && cu.getAccountPayment() > 0) {
				PaymentBean py = new PaymentBean();
				py.setCustomerNumber(insertedCustomer.getCustomerNumber());
				py.setChargePayment(cu.getAccountPayment());
				py.setAccountBalanceAtfer(insertedCustomer.getAccountBalance());
				py.setPaymentWay(cu.getPaymentWay());
				py.setGiveAmount(giveAmount);
				paymentMapper.insertPatmentWithCreateUser(py);
			}
			model.put("cu", new CustomerBean());
			model.put("in", insertedCustomer);
			model.put("successflg", true);
			model.put("chargeflg", true);
			model.put("chargeText", "充值" + cu.getAccountPayment() + "元成功，赠送" //
					+ giveAmount + "元");
		} else {
			model.put("successflg", false);
			model.put("cu", cu);
		}
		return "createCustomer";
	}

	@RequestMapping(value = "/selectCustomer", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectCustomer(Map<String, Object> model, //
			@ModelAttribute(name = "cu") CustomerBean cu) {
		List<CustomerBean> allCustomer;
		if (cu.equals(new CustomerBean())) {
			allCustomer = new ArrayList<>();
		} else {
			allCustomer = customerMapper.selectCustomer(cu);
		}
		model.put("cu", cu);
		model.put("allCustomer", allCustomer);
		return "selectCustomer";
	}

	@GetMapping(value = "/updateCustomer/{id}")
	public String updateCustomer_g(Map<String, Object> model, //
			@PathVariable(required = false, value = "id") Integer id) {
		CustomerBean cu = customerMapper.selectOne(id);
		cu.setAfterCharge(cu.getAccountBalance());
		model.put("cu", cu);
		return "updateCustomer";
	}

	@PostMapping(value = "/updateCustomer")
	public String updateCustomer_p(Map<String, Object> model, //
			@ModelAttribute CustomerBean cu) {

		customerMapper.updateCustomer(cu);
		if (cu.getAccountPayment() != null && cu.getAccountPayment() > 0) {
			int giveAmount = cu.getAfterCharge() - cu.getAccountPayment()
					- (cu.getAccountBalance() == null ? 0 : cu.getAccountBalance());
			PaymentBean py = new PaymentBean();
			BeanUtils.copyProperties(cu, py);
			py.setGiveAmount(giveAmount);
			paymentMapper.insertPatmentWithCharge(py);
			model.put("chargeflg", true);
			model.put("chargeText", "充值" + cu.getAccountPayment() + "元成功，赠送" //
					+ giveAmount + "元");
		}
		model.put("successFlg", true);
		cu = customerMapper.selectOne(cu.getCustomerNumber());
		model.put("cu", cu);
		return "updateCustomer";
	}

	@PostMapping(value = "/checkCustomer")
	@ResponseBody
	public Integer checkCustomer(@RequestParam(value = "name") String customerNmae) {
		Integer customerNumber = customerMapper.checkCustomerName(customerNmae);
		return customerNumber == null ? 0 : customerNumber;
	}

}
