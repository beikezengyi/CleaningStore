package com.cleaningstore.web.bean.condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectOrderCondition {

	private String customerName;
	
	private String customerPhoneNumber;
	
	private String otherName;
	
	private String managementNumber;
	
//	private Date createDateStart;
//	
//	private Date createDateEnd;
	
	private String createDateStart;
	
	private String createDateEnd;	

}
