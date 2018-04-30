package com.cleaningstore.web.bean.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PaymentResult {
	private Integer paymentNumber;
	private Integer customerNumber;
	private String customerName;
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
