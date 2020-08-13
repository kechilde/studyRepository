package com.zjl.o2o.service;


import com.zjl.o2o.dto.ImageHolder;
import com.zjl.o2o.dto.ShopExecution;
import com.zjl.o2o.entity.Shop;
import com.zjl.o2o.exceptions.ShopOperationException;

public interface ShopService {
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	
	/**
	 * 	添加店铺
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;
	/**
	 * 	通过店铺id获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	/**
	 * 	更新店铺信息
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;
}
