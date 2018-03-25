package com.cleaningstore.web.bean.result;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FromBean {

	 List<OrderDetailsResult> detailsList = new ArrayList<>();
}
