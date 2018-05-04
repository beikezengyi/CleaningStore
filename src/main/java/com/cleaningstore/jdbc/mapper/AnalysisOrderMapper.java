package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.cleaningstore.web.bean.condition.AnalysisOrderCondition;
import com.cleaningstore.web.bean.result.AnalysisOrderResult;

@Mapper
@Repository
public interface AnalysisOrderMapper {

	@SelectProvider(type = AnalysisOrderSqlProvider.class, method = "selectAnalysisOrderByCon")
	public List<AnalysisOrderResult> selectAnalysisOrderByCon(AnalysisOrderCondition cond);
}
