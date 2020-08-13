package com.zjl.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjl.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 	分页查询店铺，可输入的条件有：店铺名，店铺状态，店铺类别，区域ID，owner
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	/**
	 * 	查询总数
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	/**
	 * 	新增店铺 
	 * @param shop
	 * @return
	 * 
	 */
	int insertShop(Shop shop);
	
	/**
	 * 
	 * 	更新店铺信息
	 * @param shop
	 * @return
	 * 
	 * */
	int updateShop(Shop shop);
	
	/**
	 * 	查询店铺
	 * @param shop
	 * @return shop
	 */
	Shop queryByShodId(long shopId);
}
