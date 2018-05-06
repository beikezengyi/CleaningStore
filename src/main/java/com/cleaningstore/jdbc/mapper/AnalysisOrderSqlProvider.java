package com.cleaningstore.jdbc.mapper;

import com.cleaningstore.service.CleaningUtils;
import com.cleaningstore.web.bean.condition.AnalysisOrderCondition;

public class AnalysisOrderSqlProvider {

	public String selectAnalysisOrderByCon(final AnalysisOrderCondition cond) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select "//
				+ " t4.month,"//
				+ " t5.orderCount,"//
				+ " t4.thingCount,"//
				+ " t4.priceCount,"//
				+ " t4.totalPayment,"//
				+ " t4.totalPaymentByAccount,"
				+ " t6.totalCharge"//
				+ "  from (select "//
				+ " sum(washcount) as thingCount,"//
				+ " sum(t2.thingprice) as priceCount,"//
				+ " sum(t3.thingprice) as totalPayment,"//
				+ " sum(t7.thingprice) as totalPaymentByAccount,"
				+ " to_char(ordercreatedate,'yyyy-mm')as month  "//
				+ " from ordertable as t1"//
				+ " left outer join orderdetailstable as t2"//
				+ " on(t1.cleanthingnumber=t2.cleanthingnumber)"//
				+ " left outer join paymenttable as t3"//
				+ " on(t1.ordernumber=t3.ordernumber and t2.cleanthingnumber=t3.cleanthingnumber"//
				+ "  and t2.cleanthingdetailsnumber=t3.cleanthingdetailsnumber "//
				+ " and t3.paymentway!='账户余额支付')"//
				+ " left outer join paymenttable as t7"
				+ " on(t1.ordernumber=t7.ordernumber and t2.cleanthingnumber=t7.cleanthingnumber"
				+ " and t2.cleanthingdetailsnumber=t7.cleanthingdetailsnumber "
				+ " and t7.paymentway='账户余额支付')"
				+ " where t2.deletedflg=false "//
				+ " group by month) as t4"//
				+ " left outer join "//
				+ "  (select count(1)as orderCount,to_char(ordercreatedate,'yyyy-mm') as month"//
				+ "  from ordertable group by month) as t5"//
				+ " on(t4.month = t5.month)"//
				+ " left outer join (select sum(chargepayment) as totalCharge,"//
				+ " to_char(paymentdate,'yyyy-mm') as month"//
				+ " from paymenttable group by month) as t6"//
				+ " on(t4.month = t6.month)");
		CleaningUtils util = new CleaningUtils();
		boolean flg = false;
		if (util.isExist(cond.getStartMonth())) {
			sb.append(" where t4.month >= " + Squ(cond.getStartMonth()));
			flg = true;
		}
		if (util.isExist(cond.getEndMonth())) {
			if (flg) {
				sb.append(" and t4.month <= " + Squ(cond.getEndMonth()));
			} else {
				sb.append(" where t4.month <= " + Squ(cond.getEndMonth()));
			}
		}
		return sb.toString();
	}

	private String Squ(String str) {
		return "'" + str + "'";
	}
}
