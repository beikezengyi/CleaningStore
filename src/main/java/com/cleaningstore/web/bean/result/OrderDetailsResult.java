package com.cleaningstore.web.bean.result;

import java.util.ArrayList;
import java.util.List;

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
	
	private boolean created;

	private boolean toinsert;

	/** 洗涤物品一连番号 */
	private Integer cleanThingNumber;

	/** 洗涤物品一连明细番号 */
	private Integer cleanThingDetailsNumber;

	/** 物品别名 */
	private String otherName;

	private String createDate;
	private Integer washCount;

	private String washUnit;

	/** 物品类型名 */
	private Integer thingNumber;

	/** 洗涤方式名 */
	private Integer washWayNumber;

	/** 期望交付日期 */
	private String expectedDate;

	/** 实际交付日期 */
	private String realDate;

	/** 物品价格 */
	private Integer thingPrice;

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
	
	private String payStatus;

	private List<String> errormsg = new ArrayList<>();
	
	// 退避
	private String paymentWay;
	private Integer accountBalance;

}
