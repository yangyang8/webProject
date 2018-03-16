package com.hailong.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hailong.o2o.entity.Shop;

//这个类是只要是记录我们的系统的主要的店铺的主要功能类
public interface ShopDao {
	/**
	 * 这个方法是主要是用来插入我们的店铺的信息的，如店铺的注册的信息
	 * @param shop 表示的是要插入的店铺的对象
	 * @return 返回的是我们插入affect的行数
	 */
	public int insertShop(Shop shop);
	/**
	 * 这个方法是用来进行更新我们的店铺的信息的数据
	 * @param shop 表示的是要更新的店铺的对象的数据
	 * @return 返回的是我们更新affect的行数
	 */
	public int updateShop(Shop shop);
	/**
	 * 这个方法的主要的作用是根据我们的传入的id进行查询我们的商店的对象
	 * @param id	传入的商店的id
	 * @return	返回我们的符合商店的id的商店对象
	 */
	public Shop selectShopById(long id);
	
	/**
	 * 进行商铺的查询操作，使用了分页查询的操作，然后商铺名的模糊查询的，用户的id，区域名id,还有商铺的类别，商铺的状态的查询操作
	 * @param shopCondition 我们的用户的查询条件
	 * @param rowIndex  用于分页查询的开始位置
	 * @param pageSize  分页查询的网页的大小
	 * @return  返回的是分页查询后的商铺的信息
	 */
	public List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);
	
	/**
	 * 主要是用于查询我们的商铺表中的总的记录数
	 * @Param 这个参数在我们的只有一个参数的时候可以不用写，但是有多个参数的时候一定要写上，不然的话，那么则不知道那个参数是那个参数
	 * @param shopCondition 我们的用户的查询条件对象类
	 * @return 返回的是我们查询条件下的总的记录数
	 */
	public int selectShopCount(@Param("shopCondition") Shop shopCondition);
}
