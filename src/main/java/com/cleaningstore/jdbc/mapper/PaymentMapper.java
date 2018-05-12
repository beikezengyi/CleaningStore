package com.cleaningstore.jdbc.mapper;

import java.util.List;

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

	@InsertProvider(type = PaymentSqlProvider.class, method = "insertPatmentWithCreateUser")
	public int insertPatmentWithCreateUser(PaymentBean py);

	@InsertProvider(type = PaymentSqlProvider.class, method = "insertPaymentWithPayOrder")
	public int insertPatmentWithPay(PaymentBean py);

	@InsertProvider(type = PaymentSqlProvider.class, method = "insertPatmentWithCharge")
	public int insertPatmentWithCharge(PaymentBean py);

	@SelectProvider(type = PaymentSqlProvider.class, method = "selectPayment")
	public List<PaymentResult> selectPayment(SelectPaymentCondition searchPayment);
}
