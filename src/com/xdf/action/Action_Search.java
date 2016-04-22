package com.xdf.action;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Action_Search extends ActionSupport {
	private String beginDate;
	private String endDate;
	private String result;
	
	public String search(){
		
		return SUCCESS;
	}

	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
