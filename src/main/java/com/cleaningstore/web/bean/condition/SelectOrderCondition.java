package com.cleaningstore.web.bean.condition;

import java.util.Date;

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
	
	private Date createDateStart;
	
	private Date createDateEnd;

}
