package com.hailong.o2o.entity;

import java.util.Date;

//有五个成员变量
public class Area {
	//我们的区域的Id
	private Integer areaId;
	//我们的区域名
	private String areaName;
	//我们的区域的级别，根据这个级别来排序
	private Integer priority;
	//我们的这个区域的创建的时间
	private Date createTime;
	//我们的区域的更新的时间
	private Date lastEditTime;
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	
}
