package com.hailong.o2o.entity;

import java.util.Date;

//这个是我们的微信的账号的信息的实体类
public class WechatAuth {
	//我们的微信账号的id
	private Integer wechatAuthId;
	//我们的是作为微信账号和公众账号的相关联的唯一的标识
	private String openId;
	//创建的时间
	private Date createTime;
	//表示的是我们的用户的id,用来做外键
	private PersonInfo personInfo;
	public Integer getWechatAuthId() {
		return wechatAuthId;
	}
	public void setWechatAuthId(Integer wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
}
