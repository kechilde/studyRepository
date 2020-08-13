package com.zjl.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjl.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 	通过shop id 查询店铺商品类别
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	/**
	 * 	批量新增
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	/**
	 * 	删除指定商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
