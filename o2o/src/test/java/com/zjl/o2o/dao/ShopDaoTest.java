package com.zjl.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjl.o2o.BaseTest;
import com.zjl.o2o.entity.*;

public class ShopDaoTest extends BaseTest{
	@Autowired
	public ShopDao shopDao;
	
	@Test
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("大小为" + shopList.size());
		System.out.println("总数为" + count);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shopCondition.setShopCategory(sc);
		shopList = shopDao.queryShopList(shopCondition, 0, 2);
		System.out.println("大小为" + shopList.size());
	}
	
	
	
	@Test
	@Ignore
	public void testQueryByShopId() {
		long shopId = 1;
		Shop shop = shopDao.queryByShodId(shopId);
		System.out.println(shop);
	}
	
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("tesst");
		shop.setShopImg("test");
		shop.setPhone("test");
		shop.setPriority(2);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1,effectedNum);
		
	}
	
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
	
		shop.setShopName("测试店铺");
		shop.setShopDesc("测试描述（修改）");
		shop.setShopAddr("测试地址（修改）");
		shop.setLastEditTime(new Date());
		
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1,effectedNum);
		
	}
}
