package com.cleaningstore.web.bean.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 訂單明細
 * 
 * @author Administrator
 *
 */
@Getter
@Setter
@ToString
public class OrderDetailsResult {

	/** 洗涤物品一连番号 */
	private int cleanThingNumber;

	/** 洗涤物品一连明细番号 */
	private int cleanThingDetailsNumber;

	/** 物品别名 */
	private String otherName;

	private String createDate;
	private int washCount;
	private String washUnit;

	/** 物品类型名 */
	private String thingNumber;

	/** 洗涤方式名 */
	private String washWayNumber;

	/** 期望交付日期 */
	private String expectedDate;

	/** 实际交付日期 */
	private String realDate;

	/** 物品价格 */
	private int thingPrice;

	/** 管理号码/发票号码 */
	private String managementNumber;

	/** 是否上面取件 */
	private String visitToGetOrder;

	/** 是否送件上门 */
	private String visitToPutOrder;

	/** 消除旗帜 */
	private String deletedFlg;

	/** 消除日期 */
	private String deletedDate;

	private String finishflg;

	private String finishDate;

}
