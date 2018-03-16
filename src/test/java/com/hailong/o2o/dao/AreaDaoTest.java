package com.hailong.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hailong.o2o.BaseTest;
import com.hailong.o2o.entity.Area;

public class AreaDaoTest extends BaseTest {
	//创建这个Area的dao的对象
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryAreaList(){
		List<Area> list=areaDao.queryAreaList();
		assertEquals(2,list.size());
	}
}
