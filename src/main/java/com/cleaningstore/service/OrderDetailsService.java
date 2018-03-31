package com.cleaningstore.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cleaningstore.web.bean.result.OrderDetailsResult;
import com.cleaningstore.web.bean.result.YesNoBean;

@Service
public class OrderDetailsService {

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

	public boolean checkResult(List<OrderDetailsResult> detailsList) {

		for (OrderDetailsResult eachDe : detailsList) {
			if ((eachDe.isCreated() && !eachDe.getDeletedFlg().equals("t")) || eachDe.isToinsert()) {

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
}
