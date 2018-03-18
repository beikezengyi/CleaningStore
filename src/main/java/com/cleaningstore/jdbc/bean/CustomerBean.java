package com.cleaningstore.jdbc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerBean {

	private int customerNumber;
	private String customerName;
	private String customerPhoneNumber;
	private int customerSex;
	private String customerAddress;
	private int customerFamilies;
	private int accountPayment;
	private int accountBalance;

}
