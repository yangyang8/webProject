package com.hailong.o2o.entity;

import java.util.Date;

public class ProductImg {
	//商品图片的id
	private Long productImgId;
	//图片的地址
	private String imgAddr;
	//图片的描述
	private String imgDesc;
	//商品图片的优先级
	private Integer priority;
	//创建时间
	private Date createTime;
	//商品的id
	private Long produceId;
	public Long getProductImgId() {
		return productImgId;
	}
	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
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
	public Long getProduceId() {
		return produceId;
	}
	public void setProduceId(Long produceId) {
		this.produceId = produceId;
	}
}
