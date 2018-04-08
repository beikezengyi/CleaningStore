package com.cleaningstore.jdbc.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.cleaningstore.jdbc.bean.PaymentBean;

public class PaymentSqlProvider {

	public String insertPaymentWithPayOrder(final PaymentBean py) {
		return new SQL() {
			{
				INSERT_INTO("paymenttable");
				VALUES("paymentNumber", "(select coalesce(max(paymentNumber)+1,1) from paymenttable)");
				VALUES("paymentway", "#{paymentWay}");
				VALUES("consumetype", "2");// consumetype 2:消费
				VALUES("customerNumber", "#{customerNumber}");
				VALUES("orderNumber", "#{orderNumber}");
				VALUES("cleanthingnumber", "#{cleanThingNumber}");
				VALUES("cleanThingDetailsNumber", "#{cleanThingDetailsNumber}");
				VALUES("thingPrice", "#{thingPrice}");
				if (py.getPaymentWay().equals("账户余额支付")) {
					VALUES("accountBalanceBefore", "#{accountBalance}");
					VALUES("accountBalanceAtfer", "#{accountBalance} - #{thingPrice}");
				}
				VALUES("paymengtMemo", "'订单扣款'");
			}
		}.toString();
	}

}
