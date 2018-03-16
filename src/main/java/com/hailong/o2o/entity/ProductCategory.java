package com.hailong.o2o.entity;

import java.util.Date;

//我们的商品的类别实体类
public class ProductCategory {
	//商品类别的id
	private Long productCategoryId;
	//商铺的id,作为外键
	private Long shopId;
	//商品名
	private String productCategoryName;
	//优先级
	private Integer priority;
	//创建时间
	private Date createTime;
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
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
}
