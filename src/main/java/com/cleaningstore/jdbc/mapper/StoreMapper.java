package com.cleaningstore.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cleaningstore.jdbc.bean.StoreBean;

@Mapper
@Repository
public interface StoreMapper {

	@Select(value = " select * from storetable")
	public List<StoreBean> selectStore();
	
	@Insert(value = "INSERT INTO storetable" 
			+ " VALUES ((select max(storeNumber)+1 from storetable),"
			+ " #{store.storeName}, "
			+ " #{store.storeAddress})")
	public int insertStore(StoreBean store);	
}
