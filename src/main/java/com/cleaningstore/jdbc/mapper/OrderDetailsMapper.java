package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.cleaningstore.web.bean.result.OrderDetailsResult;

@Mapper
@Repository
public interface OrderDetailsMapper {

	@Select(value = "select " //
			+ " t1.cleanThingNumber,"//
			+ " cleanThingDetailsNumber,"//
			+ " true as created," //
			+ " false as toinsert," //
			+ " to_char(t1.createDate,'yyyy-mm-dd hh:mi') as createDate,"//
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
			+ " to_char(finishDate,'yyyy-mm-dd hh:mi') as finishDate,"//
			+ " payStatus," //
			+ " accountBalance" + " from orderdetailstable as t1" //
			+ " left outer join ordertable as t2"//
			+ " on(t1.cleanThingNumber=t2.cleanThingNumber)" + " left outer join customertable as t3"//
			+ " on(t2.customernumber=t3.customernumber)" + " where t2.ordernumber = #{ordernumber}"//
			+ " order by t1.cleanthingnumber asc,t1.cleanthingdetailsnumber asc;")
	public List<OrderDetailsResult> selectOrderDetails(@Param(value = "ordernumber") int ordernumber);

	@UpdateProvider(type = OrderDetailsSqlProvider.class, method = "updateOrderDetails")
	public int updateOrderDetails(OrderDetailsResult re);

	@InsertProvider(type = OrderDetailsSqlProvider.class, method = "insertOrderDetails")
	public int insertOrderDetails(OrderDetailsResult re);

	@Update(value = "update orderdetailstable"//
			+ " set paystatus ='1'"//
			+ " where cleanThingNumber=#{cleanThingNumber} and"//
			+ " cleanThingDetailsNumber=#{cleanThingDetailsNumber}")
	public int updatePayStatus(@Param(value = "re") OrderDetailsResult re);
}
