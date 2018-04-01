package com.cleaningstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleaningstore.jdbc.bean.CustomerBean;
import com.cleaningstore.jdbc.mapper.CustomerMapper;

@Service
public class CustomerService {

	@Autowired
	CleaningUtils utils;
	
	@Autowired
	CustomerMapper customerMapper;	
	
	public boolean checkCustomer(CustomerBean customerBean) {

		if (!utils.isExist(customerBean.getCustomerName())) {
			customerBean.getErrormsg().add("请输入顾客的名字！");
		}
		if (utils.isExist(customerBean.getCustomerPhoneNumber())) {
			String phone = customerBean.getCustomerPhoneNumber();
			if (phone.length() != 11) {
				customerBean.getErrormsg().add("顾客的手机号码不是11位！");
			} else if (!utils.checkPhoneNumber(phone)) {
				customerBean.getErrormsg().add("顾客的手机号码不是一个正确的格式！");
			}
		}
		if (!utils.isExist(customerBean.getCustomerSex())) {
			customerBean.getErrormsg().add("请选择顾客的性别！");
		}
		if(!customerMapper.canInsert(customerBean)) {
			customerBean.getErrormsg().add(customerBean.getCustomerName() +" 已经是会员了!");
		}
		return customerBean.getErrormsg().size() == 0;
	}
	

}
