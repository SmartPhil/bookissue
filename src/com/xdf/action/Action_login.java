package com.xdf.action;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Action_login extends ActionSupport {
	private String username;
	private String password;
	private String result;
	
	public String login() {
		HashMap<String, Object> map = new HashMap<String,Object>();
		if("admin".equals(username) && "admin123".equals(password)) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		result = JSONObject.toJSONString(map);
		return SUCCESS;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
