package com.zjl.o2o.dao;

import java.util.List;

import com.zjl.o2o.entity.ProductImg;

public interface ProductImgDao {
	/**
	 * 	批量添加商品详情图片
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
	/**
	 * 	通过productId查询产品img集合
	 * @param productId
	 * @return
	 */

	List<ProductImg> queryProductImgList(long productId);
	/**
	 * 	删除指定商品下的所有详情图
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
}
