package com.hailong.o2o.entity;

import java.util.Date;

public class PersonInfo {
	//用户可能有很多的注册，所以这个id用的是long的类型
	private Long userId;
	//用户名
	private String username;
	//用户的头像，用的是微信,的他的头像是取的是url的地址
	private String profileImg;
	//用户的邮件
	private String email;
	//用户的性别
	private String gender;
	//用户的状态，标志着这个用户是否被禁用
	private Integer enableStatus;
	//表示的是用户的类型,超级管理员权限最大
	//规定如下:1,表示的是顾客,2表示的是店家,3表示的是超级管理员 
	private Integer userType;
	//用户的注册时间
	private Date createTime;
	//用户的最后的修改时间
	private Date lastEditTime;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
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
}
