package com.cleaningstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cleaningstore.jdbc.bean.WashWayBean;
import com.cleaningstore.jdbc.mapper.WashWayMapper;
import com.cleaningstore.service.CleaningUtils;
import com.cleaningstore.web.bean.result.WebSettingBean;

@Controller
public class WashWaySettingController {

	@Autowired
	WashWayMapper washWayMapper;

	@GetMapping(value = "/washWaySetting")
	public String washWaySetting_g(Model model) {
		List<WashWayBean> wylist = washWayMapper.selectWashWay();
		WebSettingBean setting = new WebSettingBean();
		setting.setWylist(wylist);
		model.addAttribute("setting", setting);
		return "washWaySetting";
	}

	@PostMapping(value = "/washWaySetting")
	public String washWaySetting_p(Model model, //
			@ModelAttribute WebSettingBean setting) {
		List<WashWayBean> wylist = setting.getWylist();
		wylist.stream().filter(s -> s.isChecked()).forEach(s -> washWayMapper.deleteWashWay(s.getWashWayNumber()));
		wylist.stream().filter(s -> !s.isChecked()).forEach(washWayMapper::updateWashWay);
		wylist = washWayMapper.selectWashWay();
		setting.setWylist(wylist);
		setting.getMsg().add("更新成功！");
		model.addAttribute("setting", setting);
		return "washWaySetting";
	}

	@GetMapping(value = "/createWashWaySetting")
	public String createWashWay_g(Model model) {
		WebSettingBean setting = new WebSettingBean();
		setting.setWylist(createInitList());
		model.addAttribute("setting", setting);
		return "createWashWaySetting";
	}

	@PostMapping(value = "/createWashWaySetting")
	public String createWashWay_p(Model model, //
			@ModelAttribute WebSettingBean setting) {
		List<WashWayBean> wylist = setting.getWylist();
		CleaningUtils util = new CleaningUtils();
		long count = 0;
		count = wylist.stream().filter(s -> util.isExist(s.getWashWayName())).//
				peek(washWayMapper::inertWashWay).count();
		wylist = washWayMapper.selectWashWay();
		setting.setWylist(wylist);
		setting.getMsg().add(count + "件新建成功！");
		model.addAttribute("setting", setting);
		return "createWashWaySetting";
	}

	private List<WashWayBean> createInitList() {
		List<WashWayBean> wylist = new ArrayList<WashWayBean>();
		for (int i = 0; i < 5; i++) {
			wylist.add(new WashWayBean());
		}
		return wylist;
	}
}
