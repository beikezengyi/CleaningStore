package com.cleaningstore.jdbc.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.cleaningstore.jdbc.bean.CustomerBean;
import com.cleaningstore.service.CleaningUtils;

public class CustomerSqlProvider {

	CleaningUtils utils = new CleaningUtils();

	public String selectCustomer(CustomerBean cu) {
		return new SQL() {
			{
				SELECT("*");
				SELECT("case when customersex = 1 then '男士' " + "when customersex = 2 then '女士'  "
						+ "else '其他' end as customerSexStr ");
				FROM("customertable");
				if (utils.isExist(cu.getCustomerNumber())) {
					WHERE("customerNumber = #{customerNumber}");
				}
				if (utils.isExist(cu.getCustomerName())) {
					WHERE("customerName like '%'||#{customerName}||'%'");
				}
				if (utils.isExist(cu.getCustomerPhoneNumber())) {
					WHERE("customerPhoneNumber like '%'||#{customerPhoneNumber}||'%'");
				}
				if (utils.isExist(cu.getCustomerSex())) {
					WHERE("customerSex = #{customerSex}");
				}
				if (utils.isExist(cu.getCustomerAddress())) {
					WHERE("customerAddress like '%'||#{customerAddress}||'%'");
				}
			}

		}.toString();
	}

	public String updateCustomer(CustomerBean cu) {
		return new SQL() {
			{
				UPDATE("customertable");
				if (utils.isExist(cu.getCustomerPhoneNumber())) {
					SET("customerPhoneNumber = #{customerPhoneNumber}");
				}
				if (utils.isExist(cu.getCustomerSex())) {
					SET("customerSex = #{customerSex}");
				}
				if (utils.isExist(cu.getCustomerAddress())) {
					WHERE("customerAddress = #{customerAddress}");
				}
				if (utils.isExist(cu.getCustomerFamilies())) {
					WHERE("customerfamilies = #{customerfamilies}");
				}
				if (utils.isExist(cu.getCustomerAddress())) {
					WHERE("customerAddress = #{customerAddress}");
				}				
				WHERE("customerNumber = #{customerNumber}");
			}

		}.toString();
	}
}
