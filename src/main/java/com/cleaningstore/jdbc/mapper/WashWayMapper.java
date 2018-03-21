package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.WashWayBean;

@Mapper
@Repository
public interface WashWayMapper {

	@Select(value = " select * from WashWaytable")
	public List<WashWayBean> selectWashWay();
}
