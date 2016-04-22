package com.xdf.dao;

import java.util.Date;
import java.util.List;

import com.xdf.dto.BS_Class;

public interface BS_ClassDao {
	public List<BS_Class> search(Date beginDate,Date endDate);
}
