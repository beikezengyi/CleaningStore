package com.cleaningstore.jdbc.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 訂單
 * @author Administrator
 *
 */
@Getter
@Setter
@ToString
public class SelectOrderBean {

	/** 订单番号 */
	private int orderNumber;

	/** 下单日期 */
	private Date createDate;

//	/** 下单顾客番号 */
//	private int customerNumber;
	
	/** 下单顾客姓名 */
	private String customerName;
	
	/** 下单顾客电话号码 */
	private int customerPhoneNumber;	

	/** 接单店铺号 */
	private int storeNumber;

	/** 订单内物品件数 */
	private int orderDetailsCount;

	/** 洗涤物品一连番号 */
	private int cleanThingNumber;

	/** 消除旗帜 */
	private boolean deletedFlg;

	/** 消除日期 */
	private Date deletedDate;

	/** 完成旗帜 */
	private boolean finishFlg;

	/** 完成日期 */
	private Date finishDate;

}
