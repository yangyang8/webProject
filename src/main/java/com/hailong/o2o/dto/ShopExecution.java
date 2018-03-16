package com.hailong.o2o.dto;

import java.util.List;

import com.hailong.o2o.entity.Shop;
import com.hailong.o2o.enums.ShopStatusEnums;

/**
 * 这个类主要是对我们的Shop的实体进行扩展的操作的类
 * @author Administrator
 */
public class ShopExecution {
	//标识我们店铺插入状态
	private Integer status;
	//对我们的店铺插入状态描述
	private String statusInfo;
	//增加我们查询多家店铺的列表(查询店铺时可能用到)
	private List<Shop> list;
	//增加查询的店铺的数量
	private Integer count;
	//增加我们扩展的要扩展店铺属性，也就是表示操作shop(增删改店铺的时候用到)
	private Shop shop;
	
	//定义这个Shop类的扩展类的构造方法,空构造
	public ShopExecution(){}
	
	/**
	 * 定义这个Shop类的扩展类的构造方法,这个操作表示的是店铺插入操作失败时，
	 * 才会调用这个方法的
	 * @param status 表示是我们的枚举类型，里面存储的是我们的操作状态
	 */
	public ShopExecution(ShopStatusEnums status){
		this.status=status.getStatus();
		this.statusInfo=status.getStatusInfo();
	}
	/**
	 * 定义这个Shop类的扩展类的构造方法,这个操作表示的是店铺插入操作成功时，
	 * 会调用这个方法
	 * @param status 表示是我们的枚举类型，里面存储的是我们的操作状态
	 * @param shop	我们的要插入的手机
	 */
	public ShopExecution(ShopStatusEnums status,Shop shop){
		this.status=status.getStatus();
		this.statusInfo=status.getStatusInfo();
		this.shop=shop;
	}
	/**
	 * 定义这个Shop类的扩展类的构造方法,这个操作表示的是店铺插入操作成功时，
	 * 会调用这个方法
	 * @param status 表示是我们的枚举类型，里面存储的是我们的操作状态
	 * @param list  表示的是要查询的店铺的集合
	 */
	public ShopExecution(ShopStatusEnums status,List<Shop> list){
		this.status=status.getStatus();
		this.statusInfo=status.getStatusInfo();
		this.list=list;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusInfo() {
		return statusInfo;
	}
	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
	public List<Shop> getList() {
		return list;
	}
	public void setList(List<Shop> list) {
		this.list = list;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
