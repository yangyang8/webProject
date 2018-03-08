package com.hailong.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hailong.o2o.BaseTest;
import com.hailong.o2o.dao.ShopDao;
import com.hailong.o2o.dto.ShopExecution;
import com.hailong.o2o.entity.Area;
import com.hailong.o2o.entity.PersonInfo;
import com.hailong.o2o.entity.Shop;
import com.hailong.o2o.entity.ShopCategory;
import com.hailong.o2o.enums.ShopStatusEnums;

/**
 * 这个方法是用来测试我们的Serivce的方法的
 * 
 * @author Administrator
 *
 */
public class ShopServiceTest extends BaseTest {

	@Autowired
	public ShopService shopService;

	@Test
	public void testShopInsert() {
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
		area.setAreaId(2);
		user.setUserId(1L);
		shop.setArea(area);
		shop.setOwner(user);
		shop.setShopCategory(shopCategory);
		shop.setShopName("银龙3");

		shop.setShopDesc("是一个小小的店铺");
		shop.setShopAddr("广理西区1");
		shop.setPriority(2);
		//进行创建一个图片文件对象
		File fileImage=new File("G://o2o//images//yanghailong.jpg");
		FileInputStream file;
		try {
			file = new FileInputStream(fileImage);
			ShopExecution e=shopService.insertShop(shop, file,fileImage.getAbsolutePath());
			assertEquals(e.getStatus(),ShopStatusEnums.CHECK.getStatus());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}
	
	//进行测试我们的商店的修改功能
	@Test
	public void testUpdate() throws IOException{
		//创建一个Shop对象
		Shop shop=new Shop();
		shop.setShopId(8l);
		shop.setShopName("水果店");
		File srcFile=new File("G://o2o//images//fruit.jpg");
		InputStream in=new FileInputStream(srcFile);
		ShopExecution shopExecution=shopService.updateShop(shop,in,srcFile.getName());
		System.out.println(shopExecution.getStatusInfo());
		assertEquals(shopExecution.getStatus(),ShopStatusEnums.SUCCESS.getStatus());
	}
	
	@Test
	public void testQuseryList(){
		Shop shop=new Shop();
		Area a=new Area();
		a.setAreaId(2);
		shop.setArea(a);
		shop.setEnableStatus(0);
		ShopExecution shopExecution=shopService.getShopList(shop,2,4);
		System.out.println("查询的商铺的记录数:"+shopExecution.getList().size());
	}
}
