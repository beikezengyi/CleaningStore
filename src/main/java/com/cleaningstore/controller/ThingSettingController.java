package com.cleaningstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cleaningstore.jdbc.bean.ThingBean;
import com.cleaningstore.jdbc.mapper.ThingMapper;
import com.cleaningstore.service.CleaningUtils;
import com.cleaningstore.web.bean.result.WebSettingBean;

@Controller
public class ThingSettingController {

	@Autowired
	ThingMapper thingMapper;

	@GetMapping(value = "/thingSetting")
	public String thingSetting_g(Model model) {
		List<ThingBean> thlist = thingMapper.selectThing();
		WebSettingBean setting = new WebSettingBean();
		setting.setThlist(thlist);
		model.addAttribute("setting", setting);
		return "thingSetting";
	}

	@PostMapping(value = "/thingSetting")
	public String thingSetting_p(Model model, //
			@ModelAttribute WebSettingBean setting) {
		List<ThingBean> thlist = setting.getThlist();
		thlist.stream().filter(s -> !s.isChecked()).forEach(thingMapper::updateThing);

		List<Integer> deleteList = new ArrayList<>();
		thlist.stream().filter(s -> s.isChecked()).forEach(s -> deleteList.add(s.getThingNumber()));

		for (Integer num : deleteList) {
			List<Integer> cande = thingMapper.canDelete(num);
			if (cande == null || cande.size() == 0) {
				thingMapper.deleteThing(num);
			} else {
				setting.getMsg().add("番号" + num + "消除失败！下列订单正在使用该衣物。" + cande);
			}
		}
		thlist = thingMapper.selectThing();
		setting.setThlist(thlist);
		setting.getMsg().add("更新成功！");
		model.addAttribute("setting", setting);
		return "thingSetting";
	}

	@GetMapping(value = "/createThingSetting")
	public String createThing_g(Model model) {
		WebSettingBean setting = new WebSettingBean();
		setting.setThlist(createInitList());
		model.addAttribute("setting", setting);
		return "createThingSetting";
	}

	@PostMapping(value = "/createThingSetting")
	public String createThing_p(Model model, //
			@ModelAttribute WebSettingBean setting) {
		List<ThingBean> thlist = setting.getThlist();
		CleaningUtils util = new CleaningUtils();
		long count = 0;
		count = thlist.stream().filter(s -> util.isExist(s.getThingName())).//
				peek(thingMapper::inertThing).count();
		thlist = thingMapper.selectThing();
		setting.setThlist(thlist);
		setting.getMsg().add(count + "件新建成功！");
		model.addAttribute("setting", setting);
		return "createThingSetting";
	}

	@PostMapping(value = "/checkThingSetting")
	@ResponseBody
	public String checkThing_p(@RequestParam(value = "thing", required = true) String thing) {
		ThingBean t = thingMapper.selectThingIsExist(thing);
		if (t != null) {
			return t.getThingNumber().toString();
		}
		return "";
	}

	private List<ThingBean> createInitList() {
		List<ThingBean> thlist = new ArrayList<ThingBean>();
		for (int i = 0; i < 5; i++) {
			thlist.add(new ThingBean());
		}
		return thlist;
	}
}
