package com.hailong.o2o.entity;

import java.util.Date;
import java.util.List;

//创建我们的商品实体类
public class Product {
	//我们的商品的id
	private Long productId;
	//我们的商品的名字
	private String productName;
	//我们的商品的图片的地址
	private String imgAddr;
	//我们的平常价格
	private String normalPrice;
	//折扣价格
	private String promotionPrice;
	//优先级，越大越往前
	private Integer priority;
	//创建时间
	private Date createTime;
	//最后的修改时间
	private Date lastEditTime;
	//商品的状态
	private Integer enableStatus;
	//一个商品对应多个图片
	private List<ProductImg> prouctImgList;
	//商品的类别
	private ProductCategory productCategory;
	//商品所属的商店
	private Shop shop;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
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
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public List<ProductImg> getProuctImgList() {
		return prouctImgList;
	}
	public void setProuctImgList(List<ProductImg> prouctImgList) {
		this.prouctImgList = prouctImgList;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}
