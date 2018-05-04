package com.cleaningstore.web.bean.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectOrderResult {
	
	/** 订单番号 */
	private int orderNumber;
	
	/** 订单内物品件数 */
	private String cleanThingDetailsNumber;
	
	/** 订单创建日 */
	private String orderCreateDate;
	
	/** 物品下单日期 */
	private String createDate;

	/** 下单顾客姓名 */
	private String customerName;
	
	/** 下单顾客电话号码 */
	private String customerPhoneNumber;	
	
//	/** 下单顾客账户余额 */
//	private int accountbalance;		

	/** 接单店铺号 */
	private String storeName;

	/** 別名 */
	private String otherName;
	
	private String washCountWashUnitThingNumber;
	
	private String washWayName;
	
	private String expectedDate;
	private String realDate;
	private int thingPrice;
	private String managementNumber;
	private String visitOrder;

	/** 消除旗帜 */
	private String deletedFlg;

	/** 消除日期 */
	private String deletedDate;

	/** 完成旗帜 */
	private String finishFlg;

	/** 完成日期 */
	private String finishDate;
	
	private String payStatus;
}
