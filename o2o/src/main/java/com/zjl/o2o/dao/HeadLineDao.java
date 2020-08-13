package com.zjl.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjl.o2o.entity.HeadLine;

public interface HeadLineDao {
	/**
	 * 	根据传入的查询条件查询
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
