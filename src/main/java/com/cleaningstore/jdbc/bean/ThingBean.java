package com.cleaningstore.jdbc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ThingBean {
	
	private Boolean checked;
	private Integer thingNumber;
	private String thingName;
	private Integer sortNumber;

}
