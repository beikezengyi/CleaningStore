package com.cleaningstore.web.bean.condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectOrderCondition {

	private Integer orderNumber;

	private String customerName;

	private String customerPhoneNumber;

	private String otherName;

	private String managementNumber;

	// private Date createDateStart;
	//
	// private Date createDateEnd;

	/**
	 * 物件送店检索开始日
	 */
	private String createDateStart;

	/**
	 * 物件送店检索结束日
	 */
	private String createDateEnd;

	/**
	 * 订单创建检索开始日
	 */
	private String createOrderDateStart;

	/**
	 * 订单创建检索结束日
	 */
	private String createOrderDateEnd;

}
