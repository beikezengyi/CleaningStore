package com.cleaningstore.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleaningstore.jdbc.bean.CustomerBean;
import com.cleaningstore.jdbc.mapper.CustomerMapper;
import com.cleaningstore.web.bean.result.OrderDetailsResult;
import com.cleaningstore.web.bean.result.YesNoBean;

@Service
public class OrderDetailsService {

	@Autowired
	CustomerMapper customerMapper;

	public List<YesNoBean> createYesNo() {
		YesNoBean bean1 = new YesNoBean();
		bean1.setFlg("t");
		bean1.setDes("是");
		YesNoBean bean2 = new YesNoBean();
		bean2.setFlg("f");
		bean2.setDes("否");
		List<YesNoBean> list = new ArrayList<>();
		list.add(bean2);
		list.add(bean1);
		return list;
	}

	public void createNewData(int orderNumber, List<OrderDetailsResult> detailsList) {
		int detailNumber;
		if (detailsList.isEmpty()) {
			detailNumber = 1;
		} else {
			detailNumber = detailsList.get(detailsList.size() - 1).getCleanThingDetailsNumber() + 1;
		}
		OrderDetailsResult defaultData1 = new OrderDetailsResult();
		defaultData1.setCleanThingNumber(orderNumber);
		defaultData1.setCleanThingDetailsNumber(detailNumber);
		defaultData1.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		defaultData1.setCreated(false);
		defaultData1.setToinsert(false);
		OrderDetailsResult defaultData2 = new OrderDetailsResult();
		BeanUtils.copyProperties(defaultData1, defaultData2);
		defaultData2.setCleanThingDetailsNumber(detailNumber + 1);
		detailsList.add(defaultData1);
		detailsList.add(defaultData2);
	}

	public boolean checkResult(List<OrderDetailsResult> detailsList, Integer orderNumber,
			List<OrderDetailsResult> dbCurrent) {

		for (OrderDetailsResult eachDe : detailsList) {
			int dbStatus = checkStatus(dbCurrent, eachDe);

			if ((dbStatus != 2 && dbStatus != 3 // 已删除及已支付不检查入力值
					&& eachDe.isCreated() && !eachDe.getDeletedFlg().equals("t")) //
					|| eachDe.isToinsert()) {

				if (!isNotEmpty(eachDe.getCleanThingNumber())) {
					eachDe.getErrormsg().add("订单号码不存在！");
				}
				if (!isNotEmpty(eachDe.getCleanThingDetailsNumber())) {
					eachDe.getErrormsg().add("订单明细番号不存在！");
				}
				if (!isNotEmpty(eachDe.getWashCount())) {
					eachDe.getErrormsg().add("洗衣件数不能少于1件！");
				}
				if (!isNotEmpty(eachDe.getWashUnit())) {
					eachDe.getErrormsg().add("请选择洗衣单位！");
				}
				if (!isNotEmpty(eachDe.getThingNumber())) {
					eachDe.getErrormsg().add("请选择物品类型 ！");
				}
				if (!isNotEmpty(eachDe.getWashWayNumber())) {
					eachDe.getErrormsg().add("请选择洗涤方式！");
				}
				if (!isNotEmpty(eachDe.getThingPrice())) {
					eachDe.getErrormsg().add("请输入物品价格！");
				}
				if (eachDe.getFinishflg().equals("t")) {
					if (!isNotEmpty(eachDe.getFinishDate())) {
						eachDe.getErrormsg().add("完成旗帜为是的场合请添加完成日期！");
					}
				}
				if (eachDe.getDeletedFlg().equals("t")) {
					if (!isNotEmpty(eachDe.getDeletedDate())) {
						eachDe.getErrormsg().add("消除旗帜为是的场合请添加消除日期！");
					}
				}
				if (isNotEmpty(eachDe.getPayStatus()) && eachDe.getPayStatus().equals("1")
						&& isNotEmpty(eachDe.getDeletedFlg()) && !eachDe.getDeletedFlg().equals("t")) {
					// 支付订单
					if (!isNotEmpty(eachDe.getFinishDate()) //
							|| !isNotEmpty(eachDe.getFinishflg())//
							|| eachDe.getFinishflg().equals("f") //
							|| !isNotEmpty(eachDe.getRealDate())) {
						eachDe.getErrormsg().add("支付订单前请先完成订单！(更新完成旗帜,完成日期及实际交付日。)");
					}
					if (eachDe.getPaymentWay().equals("账户余额支付")) {
						CustomerBean cus = customerMapper.selectOneWithOrderNumber(orderNumber);
						int accountbal = cus.getAccountBalance() == null ? 0 : cus.getAccountBalance();
						if ((accountbal - eachDe.getThingPrice()) < 0) {
							eachDe.getErrormsg().add("顾客 " + cus.getCustomerName() + " 当前账户余额不足以支付本次订单，请充值或选择其他支付方式！");
						}
					}
				} else if (isNotEmpty(eachDe.getPayStatus())) {
					eachDe.getErrormsg().add("不能支付要删除的订单。");
				}
			}
			if (eachDe.getErrormsg().size() > 0) {
				eachDe.setPayStatus("0");
				eachDe.setDeletedFlg("f");
				eachDe.setDeletedDate(null);
			}
		}
		return detailsList.stream().filter(s -> s.getErrormsg().size() > 0).count() == 0;
	}

	private boolean isNotEmpty(Object obj) {

		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			return !((String) obj).isEmpty();
		} else if (obj instanceof Number) {
			return (int) obj > 0;
		} else if (obj instanceof Date) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param dbCurrent
	 * @param pagebean
	 * @return 0:不存在，1:存在未支付，2:存在已支付,3:已删除
	 */
	public int checkStatus(List<OrderDetailsResult> dbCurrent, OrderDetailsResult pagebean) {

		OrderDetailsResult hasbean = null;
		for (OrderDetailsResult dbcu : dbCurrent) {
			if (dbcu.getCleanThingNumber().equals(pagebean.getCleanThingNumber())
					&& dbcu.getCleanThingDetailsNumber().equals(pagebean.getCleanThingDetailsNumber())) {
				hasbean = dbcu;
				break;
			}
		}
		if (hasbean == null) {
			return 0;
		} else if (hasbean.getPayStatus() != null && hasbean.getPayStatus().equals("1")) {
			return 2;
		} else if (hasbean.getDeletedFlg() != null && hasbean.getDeletedFlg().equals("t")) {
			return 3;
		} else {
			return 1;
		}
	}
}
