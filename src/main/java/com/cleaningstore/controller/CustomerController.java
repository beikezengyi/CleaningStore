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

	@RequestMapping(value = "/createCustomer", method = { RequestMethod.GET, RequestMethod.POST })
	public String createCustomer(Map<String, Object> model, //
			@ModelAttribute(name = "cu") CustomerBean cu) {

		if (cu == null || cu.equals(new CustomerBean())) {
			cu = new CustomerBean();
		} else {
			if (service.checkCustomer(cu)) {
				// insert
				customerMapper.insertCustomer(cu);
				CustomerBean insertedCustomer = customerMapper.selectInserted(cu.getCustomerName());
				// payment
				if (cu.getAccountPayment() != null && cu.getAccountPayment() > 0) {
					PaymentBean py = new PaymentBean();
					py.setCustomerNumber(insertedCustomer.getCustomerNumber());
					py.setChargePayment(cu.getAccountPayment());
					py.setAccountBalanceAtfer(cu.getAccountBalance());
					py.setPaymentWay(cu.getPaymentWay());
					paymentMapper.insertPatmentWithCreateUser(py);
				}
				model.put("cu", insertedCustomer);
				model.put("successflg", true);
			} else {
				model.put("successflg", false);
				model.put("cu", cu);
			}
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

	@RequestMapping(value = "/updateCustomer/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateCustomer(Map<String, Object> model, //
			@PathVariable(required = false, value = "id") Integer id, //
			@ModelAttribute(name = "cu") CustomerBean cu) {

		if (id != null && id != 0) {
			CustomerBean se = new CustomerBean();
			se.setCustomerNumber(id);
			cu = customerMapper.selectOne(id);
			cu.setAfterCharge(cu.getAccountBalance());
		} else if (service.checkUpdateCustomer(cu)) {
			customerMapper.updateCustomer(cu);
			if (cu.getAccountPayment() != null && cu.getAccountPayment() > 0) {
				PaymentBean py = new PaymentBean();
				BeanUtils.copyProperties(cu, py);
				paymentMapper.insertPatmentWithCharge(py);
				model.put("chargeflg", true);
			}
			model.put("successFlg", true);
			cu = customerMapper.selectOne(cu.getCustomerNumber());
		}
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
