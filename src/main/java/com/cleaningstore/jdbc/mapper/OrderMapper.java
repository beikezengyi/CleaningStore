package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.OrderBean;
import com.cleaningstore.jdbc.bean.OrderDetailsBean;
import com.cleaningstore.web.bean.condition.SelectOrderCondition;
import com.cleaningstore.web.bean.result.SelectOrderResult;

@Mapper
@Repository
public interface OrderMapper {

	@Select(value = " select " + " t1.ordernumber, "
			+ " t2.cleanthingdetailsnumber||'(共'||t3.count||'条)' as cleanThingDetailsNumber,"
			+ " to_char(t1.ordercreatedate,'yyyy-mm-dd hh:mi') as ordercreatedate,"
			+ " to_char(t2.createDate,'yyyy-mm-dd hh:mi') as createDate," + " t4.customername,"
			+ " t4.customerphonenumber," + " t4.accountbalance," + " t5.storename," + " t2.otherName,"
			+ " t2.washcount||t2.washunit||t6.thingname as washCountWashUnitThingNumber," + " t7.washWayName,"
			+ " to_char(t2.expectedDate,'yyyy-mm-dd hh:mi') as expectedDate,"
			+ " to_char(t2.realDate,'yyyy-mm-dd hh:mi') as realDate," + " t2.thingPrice," + " t2.managementNumber,"
			+ " case when t2.visittogetorder and t2.visittoputorder then '取件送件' "
			+ "      when t2.visittogetorder and t2.visittoputorder=false then '仅取件' "
			+ "      when t2.visittogetorder=false and t2.visittoputorder then '仅送件'"
			+ "      else '' end as visitOrder," + " t2.deletedFlg,"
			+ " to_char(t2.deletedDate,'yyyy-mm-dd hh:mi') as deleteDate," + " t2.finishFlg,"
			+ " to_char(t2.finishDate,'yyyy-mm-dd hh:mi') as finishDate " +

			" from ordertable as t1" + " left outer join orderdetailstable as t2"
			+ " on(t1.cleanthingnumber=t2.cleanthingnumber)"
			+ " left outer join (select cleanthingnumber,count(1)as count from orderdetailstable group by cleanthingnumber)as t3"
			+ " on(t1.cleanthingnumber=t3.cleanthingnumber)" + " left outer join customertable as t4"
			+ " on(t1.customernumber=t4.customernumber)" + " left outer join storetable as t5"
			+ " on(t1.storenumber=t5.storenumber)" + " left outer join thingtable as t6"
			+ " on(t2.thingnumber=t6.thingnumber)" + " left outer join washwaytable as t7"
			+ " on(t2.washwaynumber=t7.washwaynumber)" + " where t2.createdate > now()- interval '1 month'"
			+ " order by t1.ordernumber asc,t2.cleanthingdetailsnumber asc;")
	public List<SelectOrderResult> selectOrder();

	@SelectProvider(type = OrderSqlProvider.class, method = "selectOrderByCond")
	public List<SelectOrderResult> selectOrderByCond(SelectOrderCondition cond);

	@Insert(value = "INSERT INTO ordertable" + " VALUES ((select max(ordernumber)+1 from ordertable),"
			+ " #{order.customerNumber}, " + " #{order.storeNumber},"
			+ " (select max(cleanthingnumber)+1 from orderdetailstable) )")
	public int insertOrder(@Param(value = "order") OrderBean order);

	@UpdateProvider(type = OrderSqlProvider.class, method = "updateOrder")
	public int updateOrder(@Param(value = "order") OrderBean order);

	@InsertProvider(type = OrderSqlProvider.class, method = "insertOrderDetails")
	public int insertOrderDetails(@Param(value = "orderDetails") OrderDetailsBean orderDetails);

	@UpdateProvider(type = OrderSqlProvider.class, method = "updateOrderDetails")
	public int updateOrderDetails(@Param(value = "orderDetails") OrderDetailsBean orderDetails);

	@Select(value = "select * from ordertable order by ordercreatedate desc,ordernumber desc limit 1;")
	public OrderBean selectMonestNew();
}
