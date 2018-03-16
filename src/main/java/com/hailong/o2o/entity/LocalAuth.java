package com.hailong.o2o.entity;

import java.util.Date;

//表示的是创建我们的本地的账号
public class LocalAuth {
	//我们的本地账号的id
	private Long localAuthId;
	//账号的用户名
	private String username;
	//我们的账号的密码
	private String password;
	//表示的是我们的创建时间
	private Date createTime;
	//表示的是最后的修改时间
	private Date lastEditTime;
	//表示的是我们的用户的id,用来做外键,做的是相关的映射
	private PersonInfo personinfo;
	public Long getLocalAuthId() {
		return localAuthId;
	}
	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public PersonInfo getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(PersonInfo personinfo) {
		this.personinfo = personinfo;
	}
	
}
