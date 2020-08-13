package com.zjl.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjl.o2o.dao.AreaDao;
import com.zjl.o2o.entity.Area;

@Service
public class AreaService implements com.zjl.o2o.service.AreaService {
	@Autowired
	private AreaDao areaDao;
	@Override
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}

}
