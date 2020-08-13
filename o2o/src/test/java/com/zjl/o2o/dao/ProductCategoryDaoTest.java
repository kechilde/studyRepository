package com.zjl.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjl.o2o.BaseTest;
import com.zjl.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
//	@Test
//	public void testQueryByShopId() throws Exception{
//		long shopId = 1;
//		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
//		System.out.println(productCategoryList.size());
//	}
//	
/*	@Test
	public void testBatchInsertProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(1L);
		
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(1);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(1L);
		
		ProductCategory productCategory3 = new ProductCategory();
		productCategory3.setProductCategoryName("商品类别2");
		productCategory3.setPriority(1);
		productCategory3.setCreateTime(new Date());
		productCategory3.setShopId(1L);
		
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		productCategoryList.add(productCategory3);
		
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		
		System.out.println(effectedNum);
	}
*/
	@Test
	public void testDeleteProductCategory() throws Exception{
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc : productCategoryList) {
			if("商品类别1".equals(pc.getProductCategoryName()) || "商品类别2".equals(pc.getProductCategoryName())) {
				int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				System.out.println(effectedNum);
			}
		}
	}
}
