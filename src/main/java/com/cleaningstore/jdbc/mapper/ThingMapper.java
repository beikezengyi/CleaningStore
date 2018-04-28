package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.ThingBean;

@Mapper
@Repository
public interface ThingMapper {

	@Select(value = " select * from thingtable "//
			+ " order by sortnumber asc, "//
			+ "thingnumber asc") //
	public List<ThingBean> selectThing();
}
