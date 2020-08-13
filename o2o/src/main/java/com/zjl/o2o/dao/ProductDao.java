package com.zjl.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjl.o2o.entity.Product;

public interface ProductDao {
	/**
	 * 	插入商品
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	/**
	 * 	通过productId查询唯一的商品信息
	 * @param productId
	 * @return
	 */
	Product queryProductById(long productId);
	/**
	 * 	更新商品信息
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	/**
	 * 	删除商品类别时，将该商品类别下的所有商品的商品类别置为空
	 * @param productCategoryId
	 * @return
	 */
	int updataProductCategoryToNull(long productCategoryId);
	/**
	 * 	查询商品集合
	 * @param productConddition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition") Product productCondition,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);
	/**
	 * 	查询对应商品总数
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);
	
}
