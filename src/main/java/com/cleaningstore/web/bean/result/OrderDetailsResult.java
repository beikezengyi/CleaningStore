package com.cleaningstore.web.bean.result;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

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
	
	private boolean viewFlg;

	/** 洗涤物品一连番号 */
	private Integer cleanThingNumber;

	/** 洗涤物品一连明细番号 */
	private Integer cleanThingDetailsNumber;

	/** 物品别名 */
	private String otherName;

	private String createDate;
	@Min(value = 1,message="件数不能低于一件。")
	private Integer washCount;
	
	@NotEmpty(message="管理号码或发票号码不能为空。")
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
	@Min(value = 1,message="价格不能低于一元。")
	private Integer thingPrice;

	/** 管理号码/发票号码 */
	@NotEmpty(message="管理号码或发票号码不能为空。")
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
