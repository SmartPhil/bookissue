package com.xdf.action;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xdf.dao.BS_ClassDao;
import com.xdf.dao.impl.BS_ClassDaoImpl;
import com.xdf.dto.BS_Class;

@SuppressWarnings("serial")
public class Action_Export extends ActionSupport {
	private String begin;
	private String end;
	
	public String export(){
		//转换查询日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;
		if ("".equals(begin) || begin == null) {
			begin = "1900-01-01";
		}
		if ("".equals(end) || end == null) {
			end = "2200-12-30";
		}
		try {
			beginDate = sdf.parse(begin);
			endDate = sdf.parse(end);
		} catch (ParseException e) {
			System.out.println("转换查询日期失败：" + e.getMessage());
		}
				
		BS_ClassDao classDao = new BS_ClassDaoImpl();
		List<BS_Class> classList = classDao.search(beginDate, endDate);
		//剔除不需要补发的班级
		List<BS_Class> classList1 = new ArrayList<BS_Class>();
		for (BS_Class bs_Class : classList) {
			if (bs_Class.getCurrentCount() > bs_Class.getDeliveryCount()) {
				bs_Class.setReIssueCount(bs_Class.getCurrentCount() - bs_Class.getDeliveryCount());
				classList1.add(bs_Class);
			}
		}
		
		String[] headers = {"班级编码","开课日期","结课日期","教材发放形式","上课时间（打印）","当前人数","最大人数","发放量","补发量","备注"};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("商机");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		for(int i = 0; i < headers.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, 32*150);
		}
		
		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		for(int i = 0; i < classList1.size(); i++){
			row = sheet.createRow(i + 1);
			BS_Class bs_Class = classList1.get(i);
			row.createCell(0).setCellValue(bs_Class.getClassCode());
			row.createCell(1).setCellValue(sdf.format(bs_Class.getBeginDate()));
			row.createCell(2).setCellValue(sdf.format(bs_Class.getEndDate()));
			row.createCell(3).setCellValue("有教材，开课前领取");
			row.createCell(4).setCellValue(bs_Class.getPrintTime());
			row.createCell(5).setCellValue(String.valueOf(bs_Class.getCurrentCount()));
			row.createCell(6).setCellValue(String.valueOf(bs_Class.getMaxCount()));
			row.createCell(7).setCellValue(String.valueOf(bs_Class.getDeliveryCount()));
			row.createCell(8).setCellValue(String.valueOf(bs_Class.getReIssueCount()));
			row.createCell(9).setCellValue(bs_Class.getComment());
			for (int j = 0; j < 10; j++) {
				row.getCell(j).setCellStyle(style2);
			}
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=BookDelivery.xls");
		try {
			OutputStream outputStream = response.getOutputStream();
			wb.write(outputStream);
			outputStream.flush();
			outputStream.close();
			wb.close();
		} catch (Exception e2) {
			System.out.println("导出失败：" + e2.getMessage());
		}
		return null;
	}
	
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
}
