package com.hailong.o2o.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 这个类主要是进行我们的从前端接收的参数进行转换成相应的数据类型的方法
 * @author Administrator
 *
 */
public class HttpServletRequestUtils {
	/**
	 * 主要是进行把我们的key中取出的字符串转换成Integer数据类型
	 * @param request http的请求对象，里面封装了所以的请求的参数和数据
	 * @param key http的请求是以key-value的形式进行封装在HttpServletRequest对象当中的
	 * @return 返回转换成Integer的数据的对象
	 */
	public static Integer getInteger(HttpServletRequest request,String key){
		//进行判断
		if(request==null){
			throw new RuntimeException("HttpServletRequest对象为空");
		}
		if(key==null||"".equals(key)){
			throw new RuntimeException("传入的http请求的key不能为空");
		}
		try{
			//进行具体的转换操作
			Integer data=Integer.decode(request.getParameter(key));
			return data;
		}catch(Exception e){
			return -1;
		}
		
	}
	
	/**
	 * 主要是进行把我们的key中取出的字符串转换成double数据类型
	 * @param request http的请求对象，里面封装了所以的请求的参数和数据
	 * @param key  http的请求是以key-value的形式进行封装在HttpServletRequest对象当中的
	 * @return  返回转换成double的数据的对象
	 */
	public static Double getDouble(HttpServletRequest request,String key){
		//进行判断
		if(request==null){
			throw new RuntimeException("HttpServletRequest对象为空");
		}
		if(key==null||"".equals(key)){
			throw new RuntimeException("传入的http请求的key不能为空");
		}
		try{
			//进行具体的转换操作
			Double data=Double.parseDouble(request.getParameter(key));
			return data;
		}catch(Exception e){
			return  -1d;
		}
		
	}
	
	/**
	 * 主要是进行把我们的key中取出的字符串转换成Long数据类型
	 * @param request http的请求对象，里面封装了所以的请求的参数和数据
	 * @param key  http的请求是以key-value的形式进行封装在HttpServletRequest对象当中的
	 * @return  返回转换成Long的数据的对象
	 */
	public static Long getLong(HttpServletRequest request,String key){
		//进行判断
		if(request==null){
			throw new RuntimeException("HttpServletRequest对象为空");
		}
		if(key==null||"".equals(key)){
			throw new RuntimeException("传入的http请求的key不能为空");
		}
		try{
			//进行具体的转换操作
			Long data=Long.decode(request.getParameter(key));
			return data;
		}catch(Exception e){
			return -1l;
		}

	}
	
	/**
	 * 主要是进行把我们的key中取出的字符串转换成Boolean数据类型
	 * @param request http的请求对象，里面封装了所以的请求的参数和数据
	 * @param key  http的请求是以key-value的形式进行封装在HttpServletRequest对象当中的
	 * @return  返回转换成boolean的数据的对象
	 */
	public static Boolean getBoolean(HttpServletRequest request,String key){
		//进行判断
		if(request==null){
			throw new RuntimeException("HttpServletRequest对象为空");
		}
		if(key==null||"".equals(key)){
			throw new RuntimeException("传入的http请求的key不能为空");
		}
		try{
			//进行具体的转换操作
			Boolean data=Boolean.valueOf(request.getParameter(key));
			return data;
		}catch(Exception e){
			//这里返回null好还是false好
			return false;
		}
	}
	
	/**
	 * 主要是进行把我们的key中取出的字符串转换成String数据类型
	 * @param request http的请求对象，里面封装了所以的请求的参数和数据
	 * @param key  http的请求是以key-value的形式进行封装在HttpServletRequest对象当中的
	 * @return  返回转换成去了空格的的字符串的对象
	 */
	public static String getString(HttpServletRequest request,String key){
		//进行判断
		if(request==null){
			throw new RuntimeException("HttpServletRequest对象为空");
		}
		if(key==null||"".equals(key)){
			throw new RuntimeException("传入的http请求的key不能为空");
		}
		try{
			//进行具体的转换操作
			String data=request.getParameter(key);
			if(data!=null&&data!=""){
				data=data.trim();
				return data;
			}else{
				return null;
			}
		}catch(Exception e){
			return null;
		}
	}
}
