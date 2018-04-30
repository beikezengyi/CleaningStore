package com.cleaningstore.web.bean.condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SelectPaymentCondition {
	private Integer customerNumber;
	private String customerName;
	private String consumeType;
	private String paymentWay;
	private Integer orderNumber;
	private String paymentDateStart;
	private String paymentDateEnd;
}
