package com.cleaningstore.jdbc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.PaymentBean;

@Mapper
@Repository
public interface PaymentMapper {

	@Insert(value = "insert into paymenttable"
			+ " (paymentNumber,paymentway,consumetype,"
			+ "customerNumber,chargePayment,accountBalanceBefore,"
			+ "accountBalanceAtfer,paymengtMemo)"
			+ " values("
			+ " (select coalesce(max(paymentNumber)+1,1) from paymenttable),"
			+ "#{paymentWay},1,#{customerNumber},#{chargePayment},"
			+ "0,#{accountBalanceAtfer},'充值')")
	public int insertPatmentWithCreateUser(PaymentBean py);
}
