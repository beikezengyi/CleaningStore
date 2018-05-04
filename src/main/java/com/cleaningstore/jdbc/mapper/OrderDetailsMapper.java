package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.cleaningstore.web.bean.result.FromBean;
import com.cleaningstore.web.bean.result.OrderDetailsResult;

@Mapper
@Repository
public interface OrderDetailsMapper {

	@Select(value = "select " //
			+ " t1.cleanThingNumber,"//
			+ " cleanThingDetailsNumber,"//
			+ " true as created," //
			+ " false as toinsert," //
			+ " to_char(t1.createDate,'yyyy-mm-dd hh24:mi') as createDate,"//
			+ " otherName,"//
			+ " thingColor,"//
			+ " washCount,"//
			+ " washUnit,"//
			+ " thingNumber,"//
			+ " washWayNumber,"//
			+ " to_char(expectedDate,'yyyy-mm-dd hh24:mi') as expectedDate,"//
			+ " to_char(realDate,'yyyy-mm-dd hh24:mi') as realDate,"//
			+ " thingPrice,"//
			+ " managementNumber,"//
			+ " visitToGetOrder,"//
			+ " visitToPutOrder,"//
			+ " deletedFlg,"//
			+ " to_char(deletedDate,'yyyy-mm-dd hh24:mi') as deletedDate,"//
			+ " deletedMemo," + " finishFlg,"//
			+ " to_char(finishDate,'yyyy-mm-dd hh24:mi') as finishDate,"//
			+ " payStatus" //
			+ " from orderdetailstable as t1" //
			+ " left outer join ordertable as t2"//
			+ " on(t1.cleanThingNumber=t2.cleanThingNumber)"//
			+ " where t2.ordernumber = #{ordernumber}"//
			+ " order by t1.cleanthingnumber asc,t1.cleanthingdetailsnumber asc;")
	public List<OrderDetailsResult> selectOrderDetails(@Param(value = "ordernumber") int ordernumber);

	@Select(value = " select t1.orderNumber,"//
			+ " t2.customerNumber,"//
			+ " t2.customerName,"//
			+ " t2.accountBalance"//
			+ " from ordertable as t1,"//
			+ " customertable as t2"//
			+ " where t1.customernumber=t2.customernumber"//
			+ " and t1.ordernumber=#{ordernumber}")
	public FromBean selectOrderInfo(@Param(value = "ordernumber") int ordernumber);

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
