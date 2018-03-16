package com.hailong.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hailong.o2o.dao.ShopCategoryDao;
import com.hailong.o2o.entity.ShopCategory;
import com.hailong.o2o.service.ShopCategoryService;
/**
 * 我们的商店的服务的操作的方法
 * @author Administrator
 *
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	//得到dao层的对象
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Override
	public List<ShopCategory> queryShopCategoryList(ShopCategory shopCategoryCondition) {
		List<ShopCategory> list=shopCategoryDao.queryShopCategoryList(shopCategoryCondition);
		return list;
	}

}
