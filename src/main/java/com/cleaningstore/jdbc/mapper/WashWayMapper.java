package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.WashWayBean;

@Mapper
@Repository
public interface WashWayMapper {

	@Select(value = " select * from WashWaytable"//
			+ " order by sortnumber desc, "//
			+ " washwaynumber asc") //
	public List<WashWayBean> selectWashWay();

	@Select(value = " select count(1)=1 from WashWaytable"//
			+ " where washWayName = #{washWayName}") //
	public boolean selectExist(@Param(value = "washWayName") String washWayName);

	@Delete(value = "delete from WashWaytable"//
			+ " where washwaynumber = #{wynumber}") //
	public int deleteWashWay(@Param(value = "wynumber") Integer wynumber);

	@Update(value = "update WashWaytable"//
			+ " set washWayName=#{wy.washWayName}, "//
			+ " sortNumber=#{wy.sortNumber}"//
			+ " where washwaynumber = #{wy.washWayNumber}") //
	public int updateWashWay(@Param(value = "wy") WashWayBean wy);

	@Update(value = "insert into WashWaytable"//
			+ " values( (select coalesce(max(washwaynumber)+1,1) from WashWaytable),"//
			+ " #{wy.washWayName}, "//
			+ " #{wy.sortNumber})") //
	public int inertWashWay(@Param(value = "wy") WashWayBean wy);
	
	@Select(value = " select t1.ordernumber from orderdetailstable as t2 " //
			+ " left outer join ordertable as t1" //
			+ " on(t1.cleanthingnumber=t2.cleanthingnumber)" //
			+ " where t2.washwaynumber=#{wynumber} "//
			+ " group by t1.ordernumber order by t1.ordernumber")
	public List<Integer> canDelete(@Param(value = "wynumber") Integer wynumber);	
}
