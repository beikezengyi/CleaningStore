package com.cleaningstore.jdbc.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.cleaningstore.jdbc.bean.PaymentBean;
import com.cleaningstore.service.CleaningUtils;
import com.cleaningstore.web.bean.condition.SelectPaymentCondition;

public class PaymentSqlProvider {

	public String insertPatmentWithCreateUser(final PaymentBean py) {
		return new SQL() {
			{
				INSERT_INTO("paymenttable");
				VALUES("paymentNumber", "(select coalesce(max(paymentNumber)+1,1) from paymenttable)");
				VALUES("paymentway", "#{paymentWay}");
				VALUES("consumetype", "1");// 1:充值
				VALUES("customerNumber", "#{customerNumber}");
				VALUES("chargePayment", "#{chargePayment}");
				VALUES("giveAmount", "#{giveAmount}");
				VALUES("accountBalanceBefore", "0");
				VALUES("accountBalanceAtfer", "#{accountBalanceAtfer}");
				VALUES("paymengtMemo", "'新用户充值'");
			}
		}.toString();
	}
	
	public String insertPaymentWithPayOrder(final PaymentBean py) {
		return new SQL() {
			{
				INSERT_INTO("paymenttable");
				VALUES("paymentNumber", "(select coalesce(max(paymentNumber)+1,1) from paymenttable)");
				VALUES("paymentway", "#{paymentWay}");
				VALUES("consumetype", "2");// consumetype 2:消费
				VALUES("customerNumber", "(select customerNumber from ordertable where ordernumber=#{orderNumber})");
				VALUES("orderNumber", "#{orderNumber}");
				VALUES("cleanthingnumber", "#{cleanThingNumber}");
				VALUES("cleanThingDetailsNumber", "#{cleanThingDetailsNumber}");
				VALUES("thingPrice", "#{thingPrice}");
				if (py.getPaymentWay().equals(CleaningUtils.paywithaccountbal)) {
					VALUES("accountBalanceBefore", "#{accountBalance}");
					VALUES("accountBalanceAtfer", "#{accountBalance} - #{thingPrice}");
				}
				VALUES("paymengtMemo", "'订单扣款'");
				VALUES("giveAmount", "#{giveAmount}");
			}
		}.toString();
	}

	public String insertPatmentWithCharge(final PaymentBean py) {
		return new SQL() {
			{
				INSERT_INTO("paymenttable");
				VALUES("paymentNumber", "(select coalesce(max(paymentNumber)+1,1) from paymenttable)");
				VALUES("paymentway", "#{paymentWay}");
				VALUES("consumetype", "1");// 1:充值
				VALUES("customerNumber", "#{customerNumber}");
				VALUES("chargePayment", "#{chargePayment}");
				VALUES("giveAmount", "#{giveAmount}");
				VALUES("accountBalanceBefore", "#{accountPayment}");
				VALUES("accountBalanceAtfer", "#{afterCharge}");
				VALUES("paymengtMemo", "'充值'");
			}
		}.toString();
	}
	
	public String selectPayment(final SelectPaymentCondition searchPayment) {
		CleaningUtils util = new CleaningUtils();
		return new SQL() {
			{
				SELECT("t1.paymentnumber");
				SELECT("t1.customernumber");
				SELECT("t1.consumetype");
				SELECT("t1.paymentway");
				SELECT("t1.ordernumber");
				SELECT("t1.cleanthingnumber");
				SELECT("t1.cleanthingdetailsnumber");
				SELECT("t1.thingprice");
				SELECT("t1.chargepayment");
				SELECT("t1.accountbalancebefore");
				SELECT("t1.accountbalanceatfer");
				SELECT("t1.paymengtmemo");
				SELECT("t1.giveAmount");
				SELECT("to_char(t1.paymentdate,'yyyy-mm-dd hh24:mi') as paymentDate");
				SELECT("t2.customername");
				FROM("paymenttable as t1 ");
				LEFT_OUTER_JOIN("customertable as  t2 on( t1.customernumber = t2.customernumber)");
				if (util.isExist(searchPayment.getCustomerNumber())) {
					WHERE("t1.customernumber = #{customerNumber}");
				}
				if (util.isExist(searchPayment.getCustomerName())) {
					WHERE("t2.customerName like '%'||#{customerName}||'%'");
				}
				if (util.isExist(searchPayment.getConsumeType())) {
					WHERE("t1.consumeType = #{consumeType}");
				}
				if (util.isExist(searchPayment.getPaymentWay())) {
					WHERE("t1.paymentWay = #{paymentWay}");
				}
				if (util.isExist(searchPayment.getPaymentDateStart())) {
					WHERE("to_char(t1.paymentdate,'yyyy-mm-dd') >= #{paymentDateStart}");
				}
				if (util.isExist(searchPayment.getPaymentDateEnd())) {
					WHERE("to_char(t1.paymentdate,'yyyy-mm-dd') <= #{paymentDateEnd}");
				}
			}
		}.toString();
	}

}
