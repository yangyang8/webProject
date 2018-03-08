package com.hailong.o2o.entity;

import java.util.Date;

//表示的是我们的用户的头条实体类
public class HeadLine {
	//表示的头条的id
	private Long lineId;
	//表示的是头条的名字
	private String lineName;
	//表示的是链接
	private String lineLink;
	//表示的是头条的图片
	private String lineImg;
	//表示的是头条的优先级
	private Integer prority;
	//表示的是头条的状态，0表示的是这个头条不能显示在前端，1表示的可以显示
	private Integer enableStatus;
	//创建时间
	private Date creteTime;
	//最后的修改时间
	private Date lastEditTime;
	public Long getLineId() {
		return lineId;
	}
	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLineLink() {
		return lineLink;
	}
	public void setLineLink(String lineLink) {
		this.lineLink = lineLink;
	}
	public String getLineImg() {
		return lineImg;
	}
	public void setLineImg(String lineImg) {
		this.lineImg = lineImg;
	}
	public Integer getPrority() {
		return prority;
	}
	public void setPrority(Integer prority) {
		this.prority = prority;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public Date getCreteTime() {
		return creteTime;
	}
	public void setCreteTime(Date creteTime) {
		this.creteTime = creteTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	

}
