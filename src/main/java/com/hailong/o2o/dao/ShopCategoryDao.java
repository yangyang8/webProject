package com.hailong.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hailong.o2o.entity.ShopCategory;

/**
 * 这个dao类主要是进行我们的商品类别的查询操作
 * @author Administrator
 *
 */
public interface ShopCategoryDao {
	//查询所有的类别的数据的操作
	public List<ShopCategory> queryShopCategoryList(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
