package com.cleaningstore.jdbc.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 訂單
 * 
 * @author Administrator
 *
 */
@Getter
@Setter
@ToString
public class OrderBean {

	/** 订单番号 */
	private int orderNumber;

	/** 下单顾客番号 */
	private int customerNumber;

	/** 接单店铺号 */
	private int storeNumber;

	/** 洗涤物品一连番号 */
	private int cleanThingNumber;
	
	private Date orderCreateDate;

}
