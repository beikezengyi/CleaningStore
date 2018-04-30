package com.cleaningstore.jdbc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ThingBean {
	
	private boolean checked;
	private Integer thingNumber;
	private String thingName;
	private Integer sortNumber;

}
