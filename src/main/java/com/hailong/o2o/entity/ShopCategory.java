package com.hailong.o2o.entity;

import java.util.Date;

//表示的是创建店铺的类别的操作
public class ShopCategory {
	//店铺的类别的id
	private Long shopCategoryId;
	//店铺的类别的名字
	private String shopCategoryName;
	//店铺的类别的描绘
	private String shopCategoryDesc;
	//店铺的类别的图片
	private String shopCategoryImg;
	//优先级
	private Integer priority;
	//具体的创建时间
	private Date createTime;
	//最后的修改的时间
	private Date lastEditTime;
	//表示的是上级id,这个也是我们的mybatis中的映射
	private ShopCategory parent;
	public Long getShopCategoryId() {
		return shopCategoryId;
	}
	public void setShopCategoryId(Long shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}
	public String getShopCategoryName() {
		return shopCategoryName;
	}
	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}
	public String getShopCategoryDesc() {
		return shopCategoryDesc;
	}
	public void setShopCategoryDesc(String shopCategoryDesc) {
		this.shopCategoryDesc = shopCategoryDesc;
	}
	public String getShopCategoryImg() {
		return shopCategoryImg;
	}
	public void setShopCategoryImg(String shopCategoryImg) {
		this.shopCategoryImg = shopCategoryImg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public ShopCategory getParent() {
		return parent;
	}
	public void setParent(ShopCategory parent) {
		this.parent = parent;
	}

}
