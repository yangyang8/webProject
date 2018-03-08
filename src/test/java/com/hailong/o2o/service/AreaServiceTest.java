package com.hailong.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hailong.o2o.BaseTest;
import com.hailong.o2o.entity.Area;

//进行测试我们的service层的操作
public class AreaServiceTest extends BaseTest{
	//得到得到我们的service的测试对象
	@Autowired
	private AreaService areaService;
	@Test
	public void testServiceQueryList(){
		List<Area> list=areaService.queryAreaList();
		assertEquals("西区",list.get(0).getAreaName());
	}
}
