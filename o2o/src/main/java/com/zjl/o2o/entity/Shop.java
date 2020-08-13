package com.zjl.o2o.entity;

import java.util.Date;

public class Shop {
	private Long shopId;
	private String shopName;
	private String shopDesc;			//店铺描述
	private String shopAddr;
	private String phone;
	private String shopImg;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	private Integer enableStatus;			//-1.不可用，0.审核中，1.可用
	private String advice;					//超级管理员给店家的提醒
	private Area area;
	private PersonInfo owner;
	private ShopCategory shopCategory;
	
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Shop(Long shopId, String shopName, String shopDesc, String shopAddr, String phone, String shopImg,
			Integer priority, Date createTime, Date lastEditTime, Integer enableStatus, String advice, Area area,
			PersonInfo owner, ShopCategory shopCategory) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopDesc = shopDesc;
		this.shopAddr = shopAddr;
		this.phone = phone;
		this.shopImg = shopImg;
		this.priority = priority;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.enableStatus = enableStatus;
		this.advice = advice;
		this.area = area;
		this.owner = owner;
		this.shopCategory = shopCategory;
	}

	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public PersonInfo getOwner() {
		return owner;
	}
	public void setOwner(PersonInfo owner) {
		this.owner = owner;
	}
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", shopDesc=" + shopDesc + ", shopAddr=" + shopAddr
				+ ", phone=" + phone + ", shopImg=" + shopImg + ", priority=" + priority + ", createTime=" + createTime
				+ ", lastEditTime=" + lastEditTime + ", enableStatus=" + enableStatus + ", advice=" + advice + ", area="
				+ area + ", oener=" + owner + ", shopCategory=" + shopCategory + "]";
	}
	
	
	
}
