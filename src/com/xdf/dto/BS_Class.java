package com.xdf.dto;

import java.util.Date;

public class BS_Class {
	private String classCode;
	private Date beginDate;
	private Date endDate;
	private String bookDeliveryType;
	private String printTime;
	private int currentCount;
	private int maxCount;
	private int deliveryCount;
	private int reIssueCount;
	private String comment;
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getBookDeliveryType() {
		return bookDeliveryType;
	}
	public void setBookDeliveryType(String bookDeliveryType) {
		this.bookDeliveryType = bookDeliveryType;
	}
	public String getPrintTime() {
		return printTime;
	}
	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public int getDeliveryCount() {
		return deliveryCount;
	}
	public void setDeliveryCount(int deliveryCount) {
		this.deliveryCount = deliveryCount;
	}
	public int getReIssueCount() {
		return reIssueCount;
	}
	public void setReIssueCount(int reIssueCount) {
		this.reIssueCount = reIssueCount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
