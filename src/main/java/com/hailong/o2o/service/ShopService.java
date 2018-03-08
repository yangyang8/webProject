package com.hailong.o2o.service;

import java.io.InputStream;

import com.hailong.o2o.dto.ShopExecution;
import com.hailong.o2o.entity.Shop;
/**
 * 我们的店铺的service层的实现方法的操作如下
 * @author Administrator
 *
 */
public interface ShopService{
	//进行插入我们的商铺的插入操作方法
	public ShopExecution insertShop(Shop shop,InputStream imageInputStream,String srcPath);
	/**
	 * 根据id查询我们的数据
	 * @param id 我们的商店的id
	 * @return   我们的查询到的商店的对象
	 */
	public Shop selectShopById(Long id);
	
	/**
	 *	我们的要根据我们的传入的对象的来修改我们的商店的数据 
	 * @param shop 我们的商店的对象
	 * @param imageInputStream  我们的图片数据流
	 * @param srcPath	我们的源图片(从前端传入的图片)的路径
	 * @return	我们的更新的结果
	 */
	public ShopExecution updateShop(Shop shop,InputStream imageInputStream,String srcPath);
	/**
	 * 根据shopCondition中根据分页查询我们的数据
	 * @param shop
	 * @param rowIndex
	 * @param pageSize
	 * @return 返回的是ShopExecution对象，这是因为我们的使用这个ShopExecution我们可以把查询到的记录数封装到这个对象当中
	 * 这个ShopExecution正好可以满足我们的条件
	 */
	public ShopExecution getShopList(Shop shop,int pageIndex,int pageSize);
}
