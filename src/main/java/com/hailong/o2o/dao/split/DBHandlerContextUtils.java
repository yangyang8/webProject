package com.hailong.o2o.dao.split;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这个类主要是提供获取数据源的信息，设置数据源的信息的一些方法的工具类
 * @author Administrator
 *
 */
public class DBHandlerContextUtils {
   //设置我们的数据库的日志功能
	private static Logger logger=LoggerFactory.getLogger(DBHandlerContextUtils.class);
	//进行创建我们的安全线程Map的集合
	private static ThreadLocal<String> dbHandler=new ThreadLocal<String>();
	//定义我们的主服务器的数据源的名字
	public static final String DB_MASTER="master";
	//定义我们的从服务器的数据源的名字
	public static final String DB_SLAVE="slave";

	//获取当前的数据源的类型
	public static String getDbType(){
		//当前的数据源的名字
		String currentDataSource=null;
		//从我们的安全线程Map的集合取出数据源的名字
		currentDataSource=dbHandler.get();
		//刚开始时为空，那么则设置默认值为从master当中，这样子安全，因为从服务器是不能写的，而主服务器都可以
		if(currentDataSource==null){
			currentDataSource=DB_MASTER;
		}
		//返回已经设置好的数据源
		return currentDataSource;
	}
	/**
	 * 我们的要给这个mybaits设置一个数据源的名称
	 * @param dataSource 我们的前面的设置的数据源的名字
	 */
	public static void setDbType(String dataSource){
		dbHandler.set(dataSource);
	}
	
	/**
	 * 进行数据源的清洗的操作
	 */
	public static void removeDB(){
		dbHandler.remove();
	}
}
