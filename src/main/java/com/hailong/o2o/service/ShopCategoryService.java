package com.hailong.o2o.service;

import java.util.List;

import com.hailong.o2o.entity.ShopCategory;

/**
 * 我们的数据的商铺类别的查询操作方法
 * @author Administrator
 *
 */
public interface ShopCategoryService {
	public List<ShopCategory> queryShopCategoryList(ShopCategory shopCategoryCondition);
}
