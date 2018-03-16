package com.hailong.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hailong.o2o.BaseTest;
import com.hailong.o2o.entity.Area;
import com.hailong.o2o.entity.PersonInfo;
import com.hailong.o2o.entity.Shop;
import com.hailong.o2o.entity.ShopCategory;

//进行测试我们的ShopDao层的代码操作
public class ShopDaoTest extends BaseTest {
	// 进行自动注入Dao对象
	@Autowired
	private ShopDao shopDao;

	@Test
	@Ignore  //表示的是让这个test注解失去作用
	public void testInsertShop() {
		// 进行创建一个Shop的对象
		Shop shop = new Shop();
		// 创建一个店铺的类别
		ShopCategory shopCategory = new ShopCategory();
		// 创建一个区域
		Area area = new Area();
		// 创建一个用户
		PersonInfo user = new PersonInfo();
		// 设置值
		shopCategory.setShopCategoryId(1L);
		area.setAreaId(1);
		user.setUserId(1L);
		shop.setArea(area);
		shop.setOwner(user);
		shop.setShopCategory(shopCategory);
		shop.setShopName("银龙");
		shop.setShopImg("test");
		shop.setShopDesc("是一个小小的店铺");
		shop.setShopAddr("广理东区");
		shop.setPriority(2);
		shop.setEnableStatus(1);
		shop.setCreateTime(new Date());
		// 进行把shop插入数据当中
		int rows = shopDao.insertShop(shop);
		assertEquals(rows, 1);
	}

	// 进行测试我们的更新店铺的操作方法
	@Test
	@Ignore
	public void testUpdateShop() {
		// 进行创建一个Shop的对象
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("银龙集团");
        shop.setAdvice("审核中");
        shop.setLastEditTime(new Date());
		// 进行把shop插入数据当中
		int rows = shopDao.updateShop(shop);
		assertEquals(rows, 1);
	}
	
	//测试我们的查询商品查询功能
	@Test
	@Ignore
	public void testSelectShopById(){
		Shop shop=shopDao.selectShopById(1);
		System.out.println(shop.getArea().getAreaName());
		System.out.println(shop.getArea().getAreaId());
	}
	
	@Test
	public void testQueryShopList(){
		Shop shop=new Shop();
		Area a=new Area();
		a.setAreaId(2);
		shop.setArea(a);
		shop.setEnableStatus(0);
		List<Shop> shopList=shopDao.queryShopList(shop,0,4);
		System.out.println("查询的商铺的记录数:"+shopList.size());
		int count=shopDao.selectShopCount(shop);
		System.out.println("查询的总的记录数:"+count);
		
	}
}
