package com.xdf.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xdf.dao.BS_ClassDao;
import com.xdf.dto.BS_Class;
import com.xdf.util.HibernateUtil;

public class BS_ClassDaoImpl implements BS_ClassDao {

	@Override
	public List<BS_Class> search(Date beginDate, Date endDate) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			String sql = "";
		} catch (Exception e) {

		}
		return null;
	}

}
