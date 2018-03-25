package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cleaningstore.web.bean.result.OrderDetailsResult;

@Mapper
@Repository
public interface OrderDetailsMapper {

	@Select(value = "select " //
			+ " orderdetailstable.cleanThingNumber,"//
			+ " cleanThingDetailsNumber,"//
			+ " to_char(createDate,'yyyy-mm-dd hh:mi') as createDate,"//
			+ " otherName,"//
			+ " washCount,"//
			+ " washUnit,"//
			+ " thingNumber,"//
			+ " washWayNumber,"//
			+ " to_char(expectedDate,'yyyy-mm-dd hh:mi') as expectedDate,"//
			+ " to_char(realDate,'yyyy-mm-dd hh:mi') as realDate,"//
			+ " thingPrice,"//
			+ " managementNumber,"//
			+ " visitToGetOrder,"//
			+ " visitToPutOrder,"//
			+ " deletedFlg,"//
			+ " to_char(deletedDate,'yyyy-mm-dd hh:mi') as deletedDate,"//
			+ " finishFlg,"//
			+ " to_char(finishDate,'yyyy-mm-dd hh:mi') as finishDate"//
			+ " from orderdetailstable "
			+ " left outer join ordertable"
			+ " on(ordertable.cleanThingNumber=orderdetailstable.cleanThingNumber)"
			+ " where ordertable.ordernumber = #{ordernumber}"//
			+ " order by cleanthingnumber asc,cleanthingdetailsnumber asc;")
	public List<OrderDetailsResult> selectOrderDetails(@Param(value = "ordernumber") int ordernumber);

	
	
}
