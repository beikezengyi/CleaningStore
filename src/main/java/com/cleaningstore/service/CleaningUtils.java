package com.cleaningstore.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CleaningUtils {

	public boolean isExist(Object obj) {

		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			String str = (String) obj;
			return !str.isEmpty();
		} else if (obj instanceof Number) {
			int num = (int) obj;
			return num != 0;
		} else {
			return obj != null;
		}
	}

	public boolean checkPhoneNumber(String phoneNumber) {
		String ptn = "^[1][0-9]{10}$";
		Pattern p = Pattern.compile(ptn);
		Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}
}
