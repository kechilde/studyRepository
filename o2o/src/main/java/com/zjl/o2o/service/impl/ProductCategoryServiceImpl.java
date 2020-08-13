package com.zjl.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjl.o2o.dao.ProductCategoryDao;
import com.zjl.o2o.dao.ProductDao;
import com.zjl.o2o.dto.ProductCategoryExecution;
import com.zjl.o2o.entity.ProductCategory;
import com.zjl.o2o.enums.ProductCategoryStateEnum;
import com.zjl.o2o.exceptions.ProductCategoryOperationExection;
import com.zjl.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategorylist)
			throws ProductCategoryOperationExection {
		if(productCategorylist != null && productCategorylist.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategorylist);
				if(effectedNum <= 0) {
					throw new ProductCategoryOperationExection("店铺类别创建失败");
				}else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			}catch(Exception e) {
				throw new ProductCategoryOperationExection("batchAddProductCategory error:" + e.getMessage());
			}
		}else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationExection {
		// 将此类别productCategoryId置为空
		try {
			int effectedNum = productDao.updataProductCategoryToNull(productCategoryId);
			if(effectedNum < 0) {
				throw new ProductCategoryOperationExection("商品类别更新失败");
			}
		}catch(Exception e) {
			throw new ProductCategoryOperationExection("deleteProductCategory error:" + e.getMessage());
		}
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum <= 0 ) {
				throw new ProductCategoryOperationExection("商品类别删除失败");
			}else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		}catch(Exception e) {
			throw new ProductCategoryOperationExection("deleteProductCategory error:" + e.getMessage());
		}
	}

}
