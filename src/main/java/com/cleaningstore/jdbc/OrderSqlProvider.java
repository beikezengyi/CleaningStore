package com.cleaningstore.jdbc;

import org.apache.ibatis.jdbc.SQL;

import com.cleaningstore.jdbc.bean.OrderBean;
import com.cleaningstore.jdbc.bean.OrderDetailsBean;
import com.cleaningstore.web.bean.condition.SelectOrderCondition;

public class OrderSqlProvider {

	public String selectOrderByCond(SelectOrderCondition cond) {
		StringBuilder sb = new StringBuilder();
		int multiCount = 0;
		sb.append("select " + " t1.ordernumber, "
				+ " t2.cleanthingdetailsnumber||'(共'||t3.count||'条)' as cleanThingDetailsNumber,"
				+ " to_char(t2.createDate,'yyyy-mm-dd hh:mi') as createDate," + " t4.customername,"
				+ " t4.customerphonenumber," + " t4.accountbalance," + " t5.storename," + " t2.otherName,"
				+ " t2.washcount||t2.washunit||t6.thingname as washCountWashUnitThingNumber," + " t7.washWayName,"
				+ " to_char(t2.expectedDate,'yyyy-mm-dd hh:mi') as expectedDate,"
				+ " to_char(t2.realDate,'yyyy-mm-dd hh:mi') as realDate," + " t2.thingPrice," + " t2.managementNumber,"
				+ " case when t2.visittogetorder and t2.visittoputorder then '取件送件' "
				+ "      when t2.visittogetorder and t2.visittoputorder=false then '仅取件' "
				+ "      when t2.visittogetorder=false and t2.visittoputorder then '仅送件'"
				+ "      else '' end as visitOrder," + " t2.deletedFlg,"
				+ " to_char(t2.deletedDate,'yyyy-mm-dd hh:mi') as deleteDate," + " t2.finishFlg,"
				+ " to_char(t2.finishDate,'yyyy-mm-dd hh:mi') as finishDate " +

				" from ordertable as t1" + " left outer join orderdetailstable as t2"
				+ " on(t1.cleanthingnumber=t2.cleanthingnumber)"
				+ " left outer join (select cleanthingnumber,count(1)as count from orderdetailstable group by cleanthingnumber)as t3"
				+ " on(t1.cleanthingnumber=t3.cleanthingnumber)" + " left outer join customertable as t4"
				+ " on(t1.customernumber=t4.customernumber)" + " left outer join storetable as t5"
				+ " on(t1.storenumber=t5.storenumber)" + " left outer join thingtable as t6"
				+ " on(t2.thingnumber=t6.thingnumber)" + " left outer join washwaytable as t7"
				+ " on(t2.washwaynumber=t7.washwaynumber)");
		if (isExist(cond.getCustomerName())) {
			sb.append("where t4.customername like '%" + cond.getCustomerName() + "%'");
			multiCount++;
		}
		if (isExist(cond.getCustomerPhoneNumber())) {
			if (multiCount > 0) {
				sb.append(" and t4.customerPhoneNumber like '%" + cond.getCustomerPhoneNumber() + "%'");
			} else {
				sb.append(" where t4.customerPhoneNumber like '%" + cond.getCustomerPhoneNumber() + "%'");
			}
			multiCount++;
		}
		if (isExist(cond.getOtherName())) {
			if (multiCount > 0) {
				sb.append(" and t2.othername like '%" + cond.getOtherName() + "%'");
			} else {
				sb.append(" where t2.othername like '%" + cond.getOtherName() + "%'");
			}
			multiCount++;
		}
		if (isExist(cond.getManagementNumber())) {
			if (multiCount > 0) {
				sb.append(" and t2.managementNumber like '%" + cond.getManagementNumber() + "%'");
			} else {
				sb.append(" where t2.managementNumber like '%" + cond.getManagementNumber() + "%'");
			}
			multiCount++;
		}
		if (isExist(cond.getCreateDateStart())) {
			if (multiCount > 0) {
				sb.append(" and t2.createDate >= " + Squ(cond.getCreateDateStart()));
			} else {
				sb.append(" where t2.createDate >= " + Squ(cond.getCreateDateStart()));
			}
			multiCount++;
		}
		if (isExist(cond.getCreateDateEnd())) {
			if (multiCount > 0) {
				sb.append(" and t2.createDate <= " + Squ(cond.getCreateDateEnd()));
			} else {
				sb.append(" where t2.createDate <= " + Squ(cond.getCreateDateEnd()));
			}
			multiCount++;
		}
		sb.append(" order by t1.ordernumber asc,t2.cleanthingdetailsnumber asc");
		return sb.toString();
	}

	private boolean isExist(Object obj) {
		if (obj instanceof String) {
			String str = (String) obj;
			return str != null && !str.isEmpty();
		} else if (obj instanceof Number) {
			int num = (int) obj;
			return num != 0;
		} else {
			return obj != null;
		}
	}

	private String Squ(String str) {
		return "'" + str + "'";
	}

	public String updateOrder(final OrderBean order) {
		return new SQL() {
			{
				UPDATE("ordertable");
				if (order.getCustomerNumber() != 0) {
					SET("customerNumber = #{order.customerNumber}");
				}
				if (order.getStoreNumber() != 0) {
					SET("storeNumber = #{order.storeNumber}");
				}
				WHERE("orderNumber = #{order.orderNumber}");
			}
		}.toString();
	}

	public String insertOrderDetails(final OrderDetailsBean orderDetails) {
		return new SQL() {
			{
				INSERT_INTO("orderdetailstable");
				VALUES("cleanThingNumber", "#{orderDetails.cleanThingNumber}");
				VALUES("cleanThingDetailsNumber",
						"select coalesce(max(cleanthingdetailsnumber)+1,1) from orderdetailstable where cleanthingnumber=#{orderDetails.cleanThingNumber}");
				VALUES("otherName", "#{orderDetails.otherName}");
				if (isExist(orderDetails.getCreateDate())) {
					VALUES("createDate", "#{orderDetails.createDate}");
				} else {
					VALUES("createDate", "current_timestamp");
				}
				VALUES("washCount", "#{orderDetails.washCount}");
				VALUES("washUnit", "#{orderDetails.washUnit}");
				VALUES("thingNumber", "#{orderDetails.thingNumber}");
				VALUES("washWayNumber", "#{orderDetails.washWayNumber}");
				VALUES("expectedDate", "#{orderDetails.expectedDate}");
				if (isExist(orderDetails.getRealDate())) {
					VALUES("realDate", "#{orderDetails.realDate}");
				}
				if (isExist(orderDetails.getThingPrice())) {
					VALUES("thingPrice", "#{orderDetails.thingPrice}");
				}
				VALUES("managementNumber", "#{orderDetails.managementNumber}");
				VALUES("visitToGetOrder", "#{orderDetails.visitToGetOrder}");
				VALUES("visitToPutOrder", "#{orderDetails.visitToPutOrder}");
				VALUES("deletedFlg", "#{orderDetails.deletedFlg}");
				if (isExist(orderDetails.getDeletedDate())) {
					VALUES("deletedDate", "#{orderDetails.deletedDate}");
				}
				VALUES("finishflg", "#{orderDetails.finishflg}");
				if (isExist(orderDetails.getFinishDate())) {
					VALUES("finishDate", "#{orderDetails.finishDate}");
				}
			}
		}.toString();
	}

	public String updateOrderDetails(final OrderDetailsBean orderDetails) {
		return new SQL() {
			{
				UPDATE("orderdetailstable");
				if (isExist(orderDetails.getOtherName())) {
					SET("othername = #{orderDetails.otherName}");
				}
				if (isExist(orderDetails.getWashCount())) {
					SET("storeNumber = #{orderDetails.washCount}");
				}
				if (isExist(orderDetails.getWashUnit())) {
					SET("washUnit = #{orderDetails.washUnit}");
				}
				if (isExist(orderDetails.getThingNumber())) {
					SET("thingNumber = #{orderDetails.thingNumber}");
				}
				if (isExist(orderDetails.getWashWayNumber())) {
					SET("washWayNumber = #{orderDetails.washWayNumber}");
				}
				if (isExist(orderDetails.getExpectedDate())) {
					SET("expectedDate = #{orderDetails.expectedDate}");
				}
				if (isExist(orderDetails.getRealDate())) {
					SET("realDate = #{orderDetails.realDate}");
				}
				if (isExist(orderDetails.getThingPrice())) {
					SET("thingPrice = #{orderDetails.thingPrice}");
				}
				if (isExist(orderDetails.getManagementNumber())) {
					SET("managementNumber = #{orderDetails.managementNumber}");
				}
				if (isExist(orderDetails.isVisitToGetOrder())) {
					SET("visitToGetOrder = #{orderDetails.visitToGetOrder}");
				}
				if (isExist(orderDetails.isVisitToPutOrder())) {
					SET("visitToPutOrder = #{orderDetails.visitToPutOrder}");
				}
				if (isExist(orderDetails.isDeletedFlg())) {
					SET("deletedFlg = #{orderDetails.deletedFlg}");
				}
				if (isExist(orderDetails.getDeletedDate())) {
					SET("deletedDate = #{orderDetails.deletedDate}");
				}
				if (isExist(orderDetails.isFinishflg())) {
					SET("finishflg = #{orderDetails.finishflg}");
				}
				if (isExist(orderDetails.getFinishDate())) {
					SET("finishDate = #{orderDetails.finishDate}");
				}
				WHERE("cleanThingNumber = #{orderDetails.cleanThingNumber}");
				WHERE("cleanThingDetailsNumber = #{orderDetails.cleanThingDetailsNumber}");
			}
		}.toString();
	}
}
