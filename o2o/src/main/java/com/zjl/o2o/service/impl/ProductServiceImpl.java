package com.zjl.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjl.o2o.dao.ProductDao;
import com.zjl.o2o.dao.ProductImgDao;
import com.zjl.o2o.dto.ImageHolder;
import com.zjl.o2o.dto.ProductExecution;
import com.zjl.o2o.entity.Product;
import com.zjl.o2o.entity.ProductImg;
import com.zjl.o2o.enums.ProductStateEnum;
import com.zjl.o2o.exceptions.ProductOperationException;
import com.zjl.o2o.service.ProductService;
import com.zjl.o2o.util.ImageUtil;
import com.zjl.o2o.util.PageCalculator;
import com.zjl.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	/**
	 * 1.处理缩略图，获取缩略图相对路径并赋值给product 2.往tb_product写入商品信息，获取produceId
	 * 3.结合produceId批量处理商品详情图 4.将商品详情图列表批量插入tb_product_img中
	 */
	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException {
		// 空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给商品设置上默认的属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// 默认为上架状态
			product.setEnableStatus(1);
			// 添加商品缩略图
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				// 创建商品信息
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败" + e.toString());
			}
			// 添加商品详情图
			if (productImgList != null && productImgList.size() > 0) {
				addProductImgList(product, productImgList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// 获取图片储存路径，这里直接存放到相应店铺的文件夹底下
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// 遍历图片一次去处理，并添加进productImg实体类
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		// 添加操作
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败：");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图片失败：" + e.toString());
			}
		}

	}

	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);

	}

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		//页码转换为数据库的行码，并调用dao层取回数据
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		//同样查询条件下商品的总数
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}

	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductById(productId);
	}

	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList) throws ProductOperationException {
		if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setLastEditTime(new Date());
			//若缩略图不为空，且原有缩略图不为空，则删除原有缩略图
			if(thumbnail != null) {
				//获取原有信息
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if(tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			//如果有传入新的缩略图，则删除原有
			if(productImgHolderList != null && productImgHolderList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product,productImgHolderList);
			}
			try {
				//更新商品信息
				int effectedNum = productDao.updateProduct(product);
				if(effectedNum <= 0) {
					throw new ProductOperationException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS,product);
			}catch(Exception e) {
				throw new ProductOperationException("更新商品信息失败:" + e.toString());
			}
		}else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	private void deleteProductImgList(Long productId) {
		//获取原来图片
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		//删除原图片
		for(ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		//删除数据库里原有的图片
		productImgDao.deleteProductImgByProductId(productId);
	}

}
