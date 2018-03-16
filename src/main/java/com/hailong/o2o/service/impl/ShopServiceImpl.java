package com.hailong.o2o.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hailong.o2o.dao.ShopDao;
import com.hailong.o2o.dto.ShopExecution;
import com.hailong.o2o.entity.Shop;
import com.hailong.o2o.enums.ShopStatusEnums;
import com.hailong.o2o.exception.ShopOperatorException;
import com.hailong.o2o.service.ShopService;
import com.hailong.o2o.utils.CalcRowIndexToPageIndex;
import com.hailong.o2o.utils.ImageUtils;
import com.hailong.o2o.utils.PathUtils;

/**
 * 这个方法是我们的service层的基本的实现操作的方法
 * 
 * @author Administrator
 *
 */
@Service
@Transactional() // 支持事务的操作
public class ShopServiceImpl implements ShopService {
	// 得到我们的ShopDao层的对象
	@Autowired
	private ShopDao shopDao;

	/**
	 * 这一层的操作的方法是我们的实现的基本的业务操作的就去
	 * 用记传入shop,那么则插入Shop的对象，然后这个shop根据我们的mybatis的设置值去得到商铺插入的id
	 * 根据这个id去得到我们的图片的相对路径，然后设置这个相对路径进去我们的数据库当中，这个过程只要有一个地方
	 * 出现问题，那么则要进行事务回滚操作，但是这个的回滚操作是只能抛出runtimeExcetion才会进行回滚操作 enable_status
	 * owner_id
	 */
	@Override
	public ShopExecution insertShop(Shop shop, InputStream imagePath, String srcPath) {
		// 进行具体的业务校验操作
		if (shop == null) {
			return new ShopExecution(ShopStatusEnums.SHOP_NULL);
		} else if (shop.getOwner() == null) {
			// 店铺主人不能为空
			return new ShopExecution(ShopStatusEnums.SHOP_OWNER);
		} /*
			 * else if(shop.getEnableStatus()==null){ //非法商铺 return new
			 * ShopExecution(ShopStatusEnums.OFFLINE); }
			 */
		// 接下来是具体的业务操作的方法,出现问题就进行回滚操作
		try {
			// 进行我们的业务的基本初始化操作,因为有些数据是我们的前端写不了的,如这个状态和注册时间(自动生成)和修改时间(自动生成)
			// 初始化我们的注册时间和修改
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 进行我们注册的状态为
			shop.setEnableStatus(ShopStatusEnums.CHECK.getStatus());
			// 进行插入我们的数据,返回我们的affect的行数
			int rows = shopDao.insertShop(shop);
			if (rows <= 0) {
				// 说明插入失败，那么则要进行回滚操作，只有抛出RuntimeExection程序才会自动回滚和结束
				throw new ShopOperatorException("店铺创建失败");
			} else {
				if (imagePath != null) {
					// 如果插入成功，那么则要根据插入的id得到我们的图片的相对的子路径，然后进行更新图片的路径
					addShopImage(shop, imagePath, srcPath);
					// 如果成功，那么则这个图片的相对路径也已经设置进行这个shop对象了，那么我们就可以直接进行更新操作了
					int updateRows = shopDao.updateShop(shop);
					if (updateRows <= 0) {
						// 说明数据更新失败，那么则要进行回滚操作，只有抛出RuntimeExection程序才会自动回滚和结束
						throw new ShopOperatorException("店铺创建失败");
					}
					// 如果没有问题，那么则进行返回插入成功
					return new ShopExecution(ShopStatusEnums.CHECK, shop);
				} else {
					throw new ShopOperatorException("图片对象为空...");
				}
			}
		} catch (Exception e) {
			// 说明插入失败，那么则要进行回滚操作，只有抛出RuntimeExection程序才会自动回滚和结束
			throw new ShopOperatorException("店铺创建失败" + e.getMessage());
		}

	}

	/**
	 * 下面的这个方法是根据我们的传入shop的id去得到我们的图片的相对路径，设置进行这个shop对象当中
	 * 
	 * @param shop
	 *            商店对象，用来得到商店的id
	 * @param imagePath
	 *            我们的传进来的图片路径
	 */
	private void addShopImage(Shop shop, InputStream imageInputStream, String srcPath) {
		// 通过PathUtils的工具类来得到我们的图片的相对路径
		String relativePath = PathUtils.getShopImagePath(shop.getShopId());
		// 返回的是相对路径
		String path = ImageUtils.genernateThumbnails(imageInputStream, relativePath, srcPath);
		// 设置这个图片的相对的路径
		shop.setShopImg(path);
	}

	/**
	 * 我们service层的根据id查询方法的具体实现
	 */
	public Shop selectShopById(Long id) {
		// 先对id进行校验操作
		if (id == null) {
			// 报错
			throw new ShopOperatorException("传统的id不能为空");
		}
		Shop shop = shopDao.selectShopById(id);
		return shop;
	}

	/**
	 * 我们的service层根据前端传入的修改对象的更新方法的具体的实现
	 */
	public ShopExecution updateShop(Shop shop, InputStream imageInputStream, String srcPath) {
		// 主要有二步,
		// 先校验操作
		if (shop != null || shop.getShopId() != null) {
			// 先根据id进行查询Shop对象
			Shop sh = shopDao.selectShopById(shop.getShopId());
			if (sh == null) {
				// 说明传入的这个用户不存在
				return new ShopExecution(ShopStatusEnums.NOFIND_SHOP);
			}
			try {
				
				if (imageInputStream != null && srcPath != null && !"".equals(srcPath)) {
					// 第一步是根据判断一下我们的图片数据是否存在，如果存在那么则要先删除图片数据，然后再更新图片数据
					String img = sh.getShopImg();
					if (img != null) {
						// 要先删除数据
						ImageUtils.deleteFileAndDir(img);
					}
					// 进行增加文件
					addShopImage(shop, imageInputStream, srcPath);
				}
				//就算是我们的imageInputStream或srcPath同样为空时，还是会执行下面的更新操作
				// 然后进行更新shop当中的数据
				// 设置一下最后修改日期
				shop.setLastEditTime(new Date());
				int rows = shopDao.updateShop(shop);
				if (rows <= 0) {
					// 返回失败
					return new ShopExecution(ShopStatusEnums.INNER_ERROR);
				} else {
					// 把更新好的数据返回给前台
					Shop s = shopDao.selectShopById(shop.getShopId());
					// 返回成功
					return new ShopExecution(ShopStatusEnums.SUCCESS, s);
				}
			} catch (Exception e) {
				throw new ShopOperatorException("数据操作出现问题" + e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * 这个方法为我们的具体的分页的操作的方法
	 */
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		//把我们的行的索引变为页的列的索引
	    int rowIndex=CalcRowIndexToPageIndex.calcPageIndex(pageIndex, pageSize);
	    //查询我们的总的数据
	    List<Shop> shopList=shopDao.queryShopList(shopCondition, rowIndex, pageSize);
	    //然后查询我们的这个查询条件当中的总的记录
	    int count=shopDao.selectShopCount(shopCondition);
	    //最后进行封装到ShopExecution当中
	    ShopExecution shopExecution=new ShopExecution();
	    if(shopList!=null&&shopList.size()!=0){
		    shopExecution.setCount(count);
		    shopExecution.setList(shopList);
	    }else{
	    	shopExecution.setStatus(ShopStatusEnums.INNER_ERROR.getStatus());
	    	shopExecution.setStatusInfo(ShopStatusEnums.INNER_ERROR.getStatusInfo());
	    }
		return shopExecution;
	}
}
