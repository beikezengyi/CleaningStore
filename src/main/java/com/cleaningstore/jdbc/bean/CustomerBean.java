package com.cleaningstore.jdbc.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CustomerBean {

	private Integer customerNumber;
	private String customerName;
	private String customerPhoneNumber;
	private Integer customerSex;
	private String customerSexStr;
	private String customerAddress;
	private Integer customerFamilies;
	private Integer accountPayment;
	private Integer accountBalance;

	private List<String> errormsg = new ArrayList<>();

}
