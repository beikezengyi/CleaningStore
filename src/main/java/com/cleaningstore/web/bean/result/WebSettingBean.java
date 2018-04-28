package com.cleaningstore.web.bean.result;

import java.util.ArrayList;
import java.util.List;

import com.cleaningstore.jdbc.bean.ThingBean;
import com.cleaningstore.jdbc.bean.WashWayBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebSettingBean {

	private List<WashWayBean> wylist;

	private List<ThingBean> thlist;

	private List<String> msg = new ArrayList<>();
}
