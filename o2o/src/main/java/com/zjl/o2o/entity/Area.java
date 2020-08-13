package com.zjl.o2o.entity;

import javax.xml.crypto.Data;

public class Area {
//	ID
	private Integer areaId;
//	名称
	private String areaName;
//	权重
	private Integer priority;
//	创建时间
	private Data createTime;
//	更新时间
	private Data lastEditTime;
	
	public Area() {
		super();
	}

	public Area(Integer areaId, String areaName, Integer priority, Data createTime, Data lastEditTime) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.priority = priority;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Data getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Data createTime) {
		this.createTime = createTime;
	}

	public Data getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Data lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
	
}
