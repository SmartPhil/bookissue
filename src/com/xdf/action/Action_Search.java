package com.xdf.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.xdf.dao.BS_ClassDao;
import com.xdf.dao.impl.BS_ClassDaoImpl;
import com.xdf.dto.BS_Class;

@SuppressWarnings("serial")
public class Action_Search extends ActionSupport {
	private String beginDate;
	private String endDate;
	private String result;
	
	public String search(){
		//ת����ѯ����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = null;
		Date end = null;
		if ("".equals(beginDate) || beginDate == null) {
			beginDate = "1900-01-01";
		}
		if ("".equals(endDate) || endDate == null) {
			endDate = "2200-12-30";
		}
		try {
			begin = sdf.parse(beginDate);
			end = sdf.parse(endDate);
		} catch (ParseException e) {
			System.out.println("ת����ѯ����ʧ�ܣ�" + e.getMessage());
		}
		
		BS_ClassDao classDao = new BS_ClassDaoImpl();
		List<BS_Class> classList = classDao.search(begin, end);
		//�޳�����Ҫ�����İ༶
		List<BS_Class> classList1 = new ArrayList<BS_Class>();
		for (BS_Class bs_Class : classList) {
			if (bs_Class.getCurrentCount() > bs_Class.getDeliveryCount()) {
				bs_Class.setReIssueCount(bs_Class.getCurrentCount() - bs_Class.getDeliveryCount());
				classList1.add(bs_Class);
			}
		}
		//�����ת��json
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		for (BS_Class bs_Class : classList1) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("code", bs_Class.getClassCode());
			map.put("beginDate", sdf.format(bs_Class.getBeginDate()));
			map.put("endDate", sdf.format(bs_Class.getEndDate()));
			map.put("bookDeliveryType", "�н̲ģ�����ǰ��ȡ");
			map.put("printTime", bs_Class.getPrintTime());
			map.put("currentCount", String.valueOf(bs_Class.getCurrentCount()));
			map.put("maxCount", String.valueOf(bs_Class.getMaxCount()));
			map.put("deliveryCount", String.valueOf(bs_Class.getDeliveryCount()));
			map.put("reIssue", String.valueOf(bs_Class.getReIssueCount()));
			if ("".equals(bs_Class.getComment()) || bs_Class.getComment() == null) {
				map.put("memo", "��");
			}else {
				map.put("memo", bs_Class.getComment());
			}
			maps.add(map);
		}
		result = JSONArray.toJSONString(maps);
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
