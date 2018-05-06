package com.cleaningstore.web.bean.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnalysisOrderResult {
	
	private String month;
	
	private int orderCount;
	
	private int thingCount;
	
	private int priceCount;
	
	private int totalPayment;
	
	private int totalPaymentByAccount;
	
	private int totalCharge;

}
