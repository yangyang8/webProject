package com.hailong.o2o.dao.split;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 因为我们的配置的是一个主从的数据服务器的操作，那么则我们就要自己写义一个路要规则进行选择具体的使用那个数据源
 * 但是要注意我们的从数据库服务器只能读不能写，因为一写的话，那么则这个主从服务器的pos就会不一致了，那么则就造成了
 * 不同步的操作
 * @author Administrator
 *
 */

public class DynamicDataSource extends AbstractRoutingDataSource{
	/**
	 * 具体的制定使用的方法那个数据源的方法
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		//得到当前数据源的配置名字
		String currentDataSouce=DBHandlerContextUtils.getDbType();
		//进行返回我们的当前的数据源的配置名字
		return currentDataSouce;
	}

}
