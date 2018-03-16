package com.hailong.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hailong.o2o.BaseTest;
import com.hailong.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	//获取dao层操作
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	//进行测试我们的dao层的操作如下
	@Test
	public void testQueryCategoryList(){
		List<ShopCategory> list=shopCategoryDao.queryShopCategoryList(new ShopCategory());
		assertEquals(2,list.size());
	}
}
