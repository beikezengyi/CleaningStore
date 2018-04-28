package com.cleaningstore.jdbc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WashWayBean {

	private boolean checked;
	private Integer washWayNumber;
	private String washWayName;
	private Integer sortNumber;
}
