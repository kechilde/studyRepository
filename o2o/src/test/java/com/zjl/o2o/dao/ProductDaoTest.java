package com.zjl.o2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjl.o2o.BaseTest;
import com.zjl.o2o.entity.Product;
import com.zjl.o2o.entity.ProductCategory;
import com.zjl.o2o.entity.Shop;

public class ProductDaoTest extends BaseTest{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	public void testInsertProduct() throws Exception{
		Shop shop1 = new Shop();
		shop1.setShopId(1L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(7L);
		Product product1 = new Product();
		product1.setProductName("测试商品1");
		product1.setProductDesc("测试商品1描述");
		product1.setImgAddr("test1");
		product1.setPriority(100);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		int effectedNum = productDao.insertProduct(product1);
		System.out.println(effectedNum);
	}

}
