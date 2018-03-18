package com.cleaningstore.jdbc.bean;

import java.util.Date;

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
public class OrderDetailsBean {

	/** 洗涤物品一连番号 */
	private int cleanThingNumber;

	/** 洗涤物品一连明细番号 */
	private int cleanThingDetailsNumber;

	/** 物品别名 */
	private String otherName;

	private Date createDate;
	private int washCount;
	private String washUnit;

	/** 物品类型名 */
	private String thingNumber;

	/** 洗涤方式名 */
	private String washWayNumber;

	/** 期望交付日期 */
	private Date expectedDate;

	/** 实际交付日期 */
	private Date realDate;

	/** 物品价格 */
	private int thingPrice;

	/** 管理号码/发票号码 */
	private String managementNumber;

	/** 是否上面取件 */
	private boolean visitToGetOrder;

	/** 是否送件上门 */
	private boolean visitToPutOrder;

	/** 消除旗帜 */
	private boolean deletedFlg;

	/** 消除日期 */
	private Date deletedDate;

	private boolean finishflg;

	private Date finishDate;

}
