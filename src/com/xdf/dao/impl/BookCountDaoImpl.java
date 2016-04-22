package com.xdf.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xdf.dao.BookCountDao;
import com.xdf.dto.BookCount;
import com.xdf.util.HibernateUtil;

public class BookCountDaoImpl implements BookCountDao {

	@Override
	public boolean insertBookCount(BookCount book) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			Integer result = (Integer)session.save(book);
			ts.commit();
			session.close();
			if (result > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("≤Â»Î ß∞‹£∫" + e.getMessage());
			ts.rollback();
			session.close();
			return false;
		}
	}

}
