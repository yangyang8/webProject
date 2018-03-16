package com.hailong.o2o.interceptor;


import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.hailong.o2o.dao.split.DBHandlerContextUtils;
/**
 * 这个类是我们的配置数据库的拦截器类
 * @author Administrator
 *
 */
@Intercepts({
	@Signature(type=Executor.class,method="update",args={MappedStatement.class,Object.class}),
	@Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {
	//设置正则表示式
	//\\u0020表示的是空格
	private final String REGEX=".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
    /**
     * 这个方法是具体的拦截的操作的方法
     */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//判段一下这个操作是否使用了事务的操作
		boolean tx=TransactionSynchronizationManager.isActualTransactionActive();
		//得到我们的传递过来的参数
		Object[] args=invocation.getArgs();
		MappedStatement ms=(MappedStatement)args[0];
		
		//设置一个默认的选择的数据库源的为
		String selectedKey=DBHandlerContextUtils.DB_MASTER;
		if(!tx){
			
			if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
				//使用从数据库服务器
				if(ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)){
					selectedKey=DBHandlerContextUtils.DB_MASTER;
				}else{
					//得到我们的这个sql操作
					BoundSql boundSql=ms.getSqlSource().getBoundSql(args[1]);
					String sql=boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]"," ");
					if(sql.matches(REGEX)){
						//进行设置为向我们的Master进行写入操作
						selectedKey=DBHandlerContextUtils.DB_MASTER;
					}else{
						//向我们的Slaves进行读取我们的数据
						selectedKey=DBHandlerContextUtils.DB_SLAVE;
					}
				}
			}
		}else{
			//那么这个操作大部分会有插入操作，则要使用主数据库服务器进行写入操作
			selectedKey=DBHandlerContextUtils.DB_MASTER;
		}
		//进行设置键
		DBHandlerContextUtils.setDbType(selectedKey);
		return invocation.proceed();
	}
	/**
	 * 这个方法主要是实现了我们的这个对象返回的是动态代理对象，还是这个对象的本身
	 */
	@Override
	public Object plugin(Object target) {
		//Executor代表的是我们的数据库当中的sql的操作，如增删改查操作
		if(target instanceof Executor){
			//那么则返回我们的动态代理对象，也就是包装类型
			return Plugin.wrap(target, this);
		}
		//直接返回对象的本身的操作
		return target;
	}
	/**
	 * 这个方法只要是进行设置我们的数据源的初始化的操作的，这个不用实现也可以
	 */
	@Override
	public void setProperties(Properties properties) {
		
	}
	
	

}
