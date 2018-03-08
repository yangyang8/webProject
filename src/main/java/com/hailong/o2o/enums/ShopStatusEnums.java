package com.hailong.o2o.enums;

public enum ShopStatusEnums {
	CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),SUCCESS(1,"操作成功")
	,PASS(2,"审核通过"),INNER_ERROR(-1001,"服务器忙"),
	NULL_SHOPID(-1002,"ShopId为空"),SHOP_NULL(-1003,"shop商铺不能为空"),
	SHOP_OWNER(-1004,"店铺主人不能为空"),NOFIND_SHOP(-1005,"请商店不存在");
	//给这个枚举类型设置属性
	private Integer status;
	private String statusInfo;
	private ShopStatusEnums(int status,String statusInfo){
		this.status=status;
		this.statusInfo=statusInfo;
	}
	//根据传入的statues的值，返回相应的enum的值
	public static ShopStatusEnums statusOf(int status){
		//进行遍历所有的枚举值
		for(ShopStatusEnums st:values()){
			if(st.status==status){
				return st;
			}
		}
		return null;
	}
	//实现这个枚举中的属性
	public Integer getStatus() {
		return status;
	}
	public String getStatusInfo() {
		return statusInfo;
	}
}
