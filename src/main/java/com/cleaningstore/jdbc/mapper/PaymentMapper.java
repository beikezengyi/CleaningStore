package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.PaymentBean;
import com.cleaningstore.web.bean.condition.SelectPaymentCondition;
import com.cleaningstore.web.bean.result.PaymentResult;

@Mapper
@Repository
public interface PaymentMapper {

	@Insert(value = "insert into paymenttable" //
			+ " (paymentNumber,paymentway,consumetype," + "customerNumber,chargePayment,accountBalanceBefore,"//
			+ "accountBalanceAtfer,paymengtMemo)" //
			+ " values("//
			+ " (select coalesce(max(paymentNumber)+1,1) from paymenttable),"//
			+ "#{paymentWay},1," // 1:充值
			+ "#{customerNumber},#{chargePayment}," + "0,#{accountBalanceAtfer},'新用户充值')")
	public int insertPatmentWithCreateUser(PaymentBean py);

	@InsertProvider(type = PaymentSqlProvider.class, method = "insertPaymentWithPayOrder")
	public int insertPatmentWithPay(PaymentBean py);

	@Insert(value = "insert into paymenttable" //
			+ " (paymentNumber,paymentway,consumetype," //
			+ "customerNumber,chargePayment,accountBalanceBefore," //
			+ "accountBalanceAtfer,paymengtMemo)" //
			+ " values("//
			+ " (select coalesce(max(paymentNumber)+1,1) from paymenttable),"//
			+ "#{paymentWay},1," // 1:充值
			+ "#{customerNumber},"//
			+ "#{accountPayment}," // 充值金额
			+ "#{accountBalance},"// 充值前
			+ "#{afterCharge},"// 充值后
			+ "'充值')")
	public int insertPatmentWithCharge(PaymentBean py);

	@SelectProvider(type = PaymentSqlProvider.class, method = "selectPayment")
	public List<PaymentResult> selectPayment(SelectPaymentCondition searchPayment);
}
