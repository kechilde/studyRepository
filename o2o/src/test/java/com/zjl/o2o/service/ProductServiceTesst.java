package com.zjl.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjl.o2o.BaseTest;
import com.zjl.o2o.dto.ImageHolder;
import com.zjl.o2o.dto.ProductExecution;
import com.zjl.o2o.entity.Product;
import com.zjl.o2o.entity.ProductCategory;
import com.zjl.o2o.entity.Shop;
import com.zjl.o2o.enums.ProductStateEnum;
import com.zjl.o2o.exceptions.ShopOperationException;

public class ProductServiceTesst extends BaseTest{
	@Autowired
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws ShopOperationException,FileNotFoundException{
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(1L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(7L);
		
		product.setProductName("测试商品3");
		product.setProductDesc("测试商品3描述");
		product.setPriority(100);
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		product.setCreateTime(new Date());
		product.setShop(shop);
		product.setProductCategory(pc);
		//缩略图
		File thumbnailFile = new File("D:\\Javaweb\\O2OAbout\\images\\test.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		//详情图
		File productImg1 = new File("D:\\Javaweb\\O2OAbout\\images\\test.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		
		File productImg2 = new File("D:\\Javaweb\\O2OAbout\\images\\test.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		//验证
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
		System.out.println(pe.getState());
	}
}
