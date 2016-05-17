package com.xdf.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.xdf.dao.BookCountDao;
import com.xdf.dao.impl.BookCountDaoImpl;
import com.xdf.dto.BookCount;
import com.xdf.util.ExcelReader;

@SuppressWarnings("serial")
public class Action_ImportExcel extends ActionSupport {
	private File file_upload;
	private String fileName;
	private String flag;
	private Object result;
	private String username;
	public String importExcel(){
		System.out.println(flag);
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/UploadFiles");
			File target = new File(path, "file_upload.xlsx");
			try {
				FileUtils.copyFile(file_upload, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(target.getPath());
			ExcelReader excelReader = new ExcelReader(target.getPath());
			List<String[]> resultList = excelReader.readAllData();
			List<BookCount> bookCountList = new ArrayList<BookCount>();
			BookCountDao bookCountDao = new BookCountDaoImpl();
			//获取所有配送信息
			List<BookCount> bookCounts = bookCountDao.getAllBookCount();
			if(resultList.size() >= 1){
				for (int i = 0; i < resultList.size(); i++) {
					BookCount bookcount = new BookCount();
					bookcount.setClassCode(resultList.get(i)[0]);
					if(!"".equals(resultList.get(i)[1]) && resultList.get(i)[1] != null) {
						bookcount.setCount(Integer.parseInt(resultList.get(i)[1]));
					}else {
						bookcount.setCount(0);
					}
					bookCountList.add(bookcount);
				}
				
				//保存数据
				if ("0".equals(flag)) {
					for (BookCount bookCount : bookCountList) {
				    	int mark = 0;
						for (BookCount bookCount1 : bookCounts) {
							if (bookCount1.getClassCode().equals(bookCount.getClassCode())) {
								bookCount1.setCount(bookCount.getCount());
								bookCountDao.updateBookCount(bookCount1);
								mark = 1; 
								break;
							}
						}
						if (mark == 0) {
							bookCountDao.insertBookCount(bookCount);
						}
					}
				}else if ("1".equals(flag)) {
					for (BookCount bookCount : bookCountList) {
				    	int mark = 0;
						for (BookCount bookCount1 : bookCounts) {
							if (bookCount1.getClassCode().equals(bookCount.getClassCode())) {
								bookCount1.setCount(bookCount.getCount() + bookCount1.getCount());
								bookCountDao.updateBookCount(bookCount1);
								mark = 1;
								break;
							}
						}
						if (mark == 0) {
							bookCountDao.insertBookCount(bookCount);
						}
					}
				}
			    
				map.put("result", "success");
			}else {
				System.out.println("上传的是空文件！");
				map.put("result", "null");
			}
		} catch (Exception e) {
			System.out.println("批量上传失败：Action_ImportExcel.java:" + e.getMessage());
			map.put("result", "fail");
		}
		result = JSON.toJSON(map);
		return SUCCESS;
	}
	
	public File getFile_upload() {
		return file_upload;
	}
	public void setFile_upload(File file_upload) {
		this.file_upload = file_upload;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
