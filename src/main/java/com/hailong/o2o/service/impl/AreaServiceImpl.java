package com.hailong.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hailong.o2o.dao.AreaDao;
import com.hailong.o2o.entity.Area;
import com.hailong.o2o.service.AreaService;
@Service  //表示把AreaServiceImpl加入spring的容器当中
public class AreaServiceImpl implements AreaService {
	//进行得到我们的dao层的对象，这个Dao层的对象不用我们自已来维护和自己来加入Spring的容器
	@Autowired
	private AreaDao areaDao;
	@Override
	public List<Area> queryAreaList() {
		List<Area> areaList=areaDao.queryAreaList();
		return areaList;
	}

}
