package com.zjl.o2o.service;

import java.util.List;

import com.zjl.o2o.dto.ProductCategoryExecution;
import com.zjl.o2o.entity.ProductCategory;
import com.zjl.o2o.exceptions.ProductCategoryOperationExection;

public interface ProductCategoryService {
	/**
	 * 	查询某个店铺下的所有商品类别信息
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	/**
	 * 	批量添加商品类别
	 * @param productCategorylist
	 * @return
	 * @throws ProductCategoryOperationExection
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategorylist)
		throws ProductCategoryOperationExection;
	/**
	 * 	将此类别productCategoryId置为空，再删掉该商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationExection
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)
			throws ProductCategoryOperationExection;
}
