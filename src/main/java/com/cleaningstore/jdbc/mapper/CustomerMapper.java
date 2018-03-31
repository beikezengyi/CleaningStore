package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.CustomerBean;

@Mapper
@Repository
public interface CustomerMapper {

	@Select(value = " select * from customertable")
	public List<CustomerBean> selectCustomer();

	@Select(value = " select * from customertable where "//
			+ "customerName like '%'||#{customerName}||'%'" //
			+ " and customerPhoneNumber like '%'||#{customerPhoneNumber}||'%'")
	public List<CustomerBean> selectCustomerByCon(//
			@Param(value = "customerName") String customerName, //
			@Param(value = "customerPhoneNumber") String customerPhoneNumber);
	
	@Select(value = " select * from customertable where customerNumber = #{id}")
	public CustomerBean selectOne(@Param(value = "id") int id);

	@Insert(value = "INSERT INTO customertable" //
			+ " VALUES ((select max(customerNumber)+1 from customertable),"//
			+ " #{customer.customerName}," //
			+ "#{customer.customerPhoneNumber}," //
			+ "#{customer.customerSex},"//
			+ "#{customer.customerAddress}," //
			+ "#{customer.customerFamilies}," //
			+ "#{customer.accountPayment},"//
			+ "#{customer.accountBalance}")
	public int insertCustomer(@Param(value = "customer") CustomerBean customer);
}