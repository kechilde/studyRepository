package com.zjl.o2o.service;

import java.util.List;

import com.zjl.o2o.dto.ImageHolder;
import com.zjl.o2o.dto.ProductExecution;
import com.zjl.o2o.entity.Product;
import com.zjl.o2o.exceptions.ProductOperationException;

public interface ProductService {
	/**
	 * 	查询商品列表并分页
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
	/**
	 * 	通过商品Id查询唯一的商品信息
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);
	
	/**
	 * 商品添加信息以及图片处理
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductOperationException;
	/**
	 * 	修改商品信息以及图片处理
	 * @param product
	 * @param thumbnail
	 * @param productImgHolderList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product,ImageHolder thumbnail,List<ImageHolder> productImgHolderList)
		throws ProductOperationException;
}
