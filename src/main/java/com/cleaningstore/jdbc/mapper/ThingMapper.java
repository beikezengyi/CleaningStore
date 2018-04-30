package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.ThingBean;

@Mapper
@Repository
public interface ThingMapper {

	@Select(value = " select * from thingtable "//
			+ " order by sortnumber desc, "//
			+ "thingnumber asc") //
	public List<ThingBean> selectThing();

	@Select(value = " select count(1)=1 from thingtable"//
			+ " where thingName = #{thingName}") //
	public boolean selectExist(@Param(value = "thingName") String thingName);

	@Delete(value = "delete from thingtable"//
			+ " where thingnumber = #{thnumber}") //
	public int deleteThing(@Param(value = "thnumber") Integer thnumber);

	@Update(value = "update thingtable"//
			+ " set thingName=#{th.thingName}, "//
			+ " sortNumber=#{th.sortNumber}"//
			+ " where thingnumber = #{th.thingNumber}") //
	public int updateThing(@Param(value = "th") ThingBean th);

	@Update(value = "insert into thingtable"//
			+ " values( (select coalesce(max(thingnumber)+1,1) from thingtable),"//
			+ " #{th.thingName}, "//
			+ " #{th.sortNumber})") //
	public int inertThing(@Param(value = "th") ThingBean th);

	@Select(value = " select t1.ordernumber from orderdetailstable as t2 " //
			+ " left outer join ordertable as t1" //
			+ " on(t1.cleanthingnumber=t2.cleanthingnumber)" //
			+ " where t2.thingnumber=#{thnumber} "//
			+ " group by t1.ordernumber order by t1.ordernumber")
	public List<Integer> canDelete(@Param(value = "thnumber") Integer thnumber);
}
