package com.hailong.o2o.dao;

import java.util.List;

import com.hailong.o2o.entity.Area;

/**
 * 这个类是我们的Aera的区域的配置接口,这个接口的实现类我们可以不用配置，因为我们的mybatis自动使用
 * mapper接口映射的动态代理的方式帮我们去实现 这个接口类
 * @author Administrator
 *
 */
public interface AreaDao {
	public List<Area> queryAreaList();
}
