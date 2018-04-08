package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.CustomerBean;

@Mapper
@Repository
public interface CustomerMapper {

	@Select(value = " select *," + "case when customersex = 1 then '男士'" + " when customersex = 2 then '女士' "
			+ " else '其他' end as customerSexStr " + "from customertable where "//
			+ "customerName like '%'||#{customerName}||'%'" //
			+ " and customerPhoneNumber like '%'||#{customerPhoneNumber}||'%'")
	public List<CustomerBean> selectCustomerByCon(//
			@Param(value = "customerName") String customerName, //
			@Param(value = "customerPhoneNumber") String customerPhoneNumber);

	@Select(value = " select * from customertable where customerNumber = #{id}")
	public CustomerBean selectOne(@Param(value = "id") int id);

	@Select(value = "select t1.* from customertable t1,ordertable t2 " //
			+ " where t2.ordernumber=#{orderNumber}"//
			+ " and t1.customernumber=t2.customernumber")
	public CustomerBean selectOneWithOrderNumber(@Param(value = "orderNumber") int orderNumber);

	@Insert(value = "INSERT INTO customertable" //
			+ " VALUES ((select coalesce(max(customerNumber)+1,1) from customertable),"//
			+ " #{customer.customerName}," //
			+ "#{customer.customerPhoneNumber}," //
			+ "#{customer.customerSex},"//
			+ "#{customer.customerAddress}," //
			+ "#{customer.customerFamilies}," //
			+ "#{customer.accountBalance}" + ")")
	public int insertCustomer(@Param(value = "customer") CustomerBean customer);

	@Select(value = "select count(1) = 0 " + " from customertable" + " where customerName=#{customer.customerName}")
	public boolean canInsert(@Param(value = "customer") CustomerBean customer);

	@Select(value = " select *," + "case when customersex = 1 then '男士'" + " when customersex = 2 then '女士' "
			+ " else '其他' end as customerSexStr " + " from customertable where customerName = #{customerName}")
	public CustomerBean selectInserted(@Param(value = "customerName") String customerName);

	@SelectProvider(type = CustomerSqlProvider.class, method = "selectCustomer")
	public List<CustomerBean> selectCustomer(CustomerBean cu);

	@UpdateProvider(type = CustomerSqlProvider.class, method = "updateCustomer")
	public int updateCustomer(CustomerBean cu);

	@Update(value = "update customertable "//
			+ " set accountbalance=accountbalance-#{price}"//
			+ " where customerNumber = "//
			+ "(select customerNumber from ordertable where ordernumber=#{orderNumber})")
	public int updateCustomerPaied(@Param(value = "orderNumber") Integer orderNumber,
			@Param(value = "price") Integer price);
}
