package com.cleaningstore.jdbc.mapper;

import java.util.Date;

import org.apache.ibatis.jdbc.SQL;

import com.cleaningstore.web.bean.result.OrderDetailsResult;

public class OrderDetailsSqlProvider {

	public String updateOrderDetails(OrderDetailsResult re) {
		return new SQL() {
			{
				UPDATE("orderdetailstable");
				if (isNotEmpty(re.getOtherName())) {
					SET("otherName = #{otherName}");
				}
				if (isNotEmpty(re.getWashCount())) {
					SET("washCount = #{washCount}");
				}
				if (isNotEmpty(re.getWashUnit())) {
					SET("washUnit = #{washUnit}");
				}
				SET("thingNumber = #{thingNumber}");
				SET("washWayNumber = #{washWayNumber}");
				if (isNotEmpty(re.getExpectedDate())) {
					SET("expectedDate = to_timestamp(#{expectedDate},'yyyy-mm-dd hh24:mi')");
				}
				if (isNotEmpty(re.getRealDate())) {
					SET("realDate = to_timestamp(#{realDate},'yyyy-mm-dd hh24:mi')");
				}
				if (isNotEmpty(re.getThingPrice())) {
					SET("thingPrice = #{thingPrice}");
				}
				if (isNotEmpty(re.getManagementNumber())) {
					SET("managementNumber = #{managementNumber}");
				}
				SET("visitToGetOrder = #{visitToGetOrder}::boolean");
				SET("visitToPutOrder = #{visitToPutOrder}::boolean");
				SET("deletedFlg = #{deletedFlg}::boolean");
				if (isNotEmpty(re.getDeletedDate())) {
					SET("deletedDate = to_timestamp(#{deletedDate},'yyyy-mm-dd hh24:mi')");
				}
				SET("finishflg = #{finishflg}::boolean");
				if (isNotEmpty(re.getFinishDate())) {
					SET("finishDate = to_timestamp(#{finishDate},'yyyy-mm-dd hh24:mi')");
				}
				if (isNotEmpty(re.getPayStatus())) {
					SET("payStatus = #{payStatus}");
				}
				WHERE("cleanThingNumber = #{cleanThingNumber}");
				WHERE("cleanThingDetailsNumber = #{cleanThingDetailsNumber}");
			}
		}.toString();
	}

	public String insertOrderDetails(OrderDetailsResult re) {
		return new SQL() {
			{
				INSERT_INTO("orderdetailstable");
				VALUES("cleanThingNumber", "#{cleanThingNumber}");
				VALUES("cleanThingDetailsNumber",
						"(select count(1)+1 from orderdetailstable where cleanThingNumber=#{cleanThingNumber})");
				if (isNotEmpty(re.getOtherName())) {
					VALUES("otherName", "#{otherName}");
				}
				if (isNotEmpty(re.getWashCount())) {
					VALUES("washCount", "#{washCount}");
				}
				if (isNotEmpty(re.getWashUnit())) {
					VALUES("washUnit", "#{washUnit}");
				}
				VALUES("thingNumber", "#{thingNumber}");
				VALUES("washWayNumber", "#{washWayNumber}");
				if (isNotEmpty(re.getExpectedDate())) {
					VALUES("expectedDate", "to_timestamp(#{expectedDate},'yyyy-mm-dd hh24:mi')");
				}
				if (isNotEmpty(re.getRealDate())) {
					VALUES("realDate", "to_timestamp(#{realDate},'yyyy-mm-dd hh24:mi')");
				}
				if (isNotEmpty(re.getThingPrice())) {
					VALUES("thingPrice", "#{thingPrice}");
				}
				if (isNotEmpty(re.getManagementNumber())) {
					VALUES("managementNumber", "#{managementNumber}");
				}
				VALUES("visitToGetOrder", "#{visitToGetOrder}::boolean");
				VALUES("visitToPutOrder", "#{visitToPutOrder}::boolean");
				VALUES("deletedFlg", "#{deletedFlg}::boolean");
				if (isNotEmpty(re.getDeletedDate())) {
					VALUES("deletedDate", "to_timestamp(#{deletedDate},'yyyy-mm-dd hh24:mi')");
				}
				VALUES("finishflg", "#{finishflg}::boolean");
				if (isNotEmpty(re.getFinishDate())) {
					VALUES("finishDate", "to_timestamp(#{finishDate},'yyyy-mm-dd hh24:mi')");
				}
				if (isNotEmpty(re.getPayStatus())) {
					VALUES("payStatus", "#{payStatus}");
				}
			}
		}.toString();
	}

	private boolean isNotEmpty(Object obj) {

		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			return !((String) obj).isEmpty();
		} else if (obj instanceof Number) {
			return (int) obj > 0;
		} else if (obj instanceof Date) {
			return true;
		}

		return false;
	}
}
