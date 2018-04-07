package com.cleaningstore.jdbc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PaymentBean {

	private Integer paymentNumber;
	private Integer customerNumber;
	private String consumeType;
	private String paymentWay;
	private Integer orderNumber;
	private Integer cleanThingNumber;
	private Integer cleanThingDetailsNumber;
	private Integer chargePayment;
	private Integer thingPrice;
	private Integer accountBalanceBefore;
	private Integer accountBalanceAtfer;
	private String paymengtMemo;
	private String paymentdate;
	
	
}