package com.xdf.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
			System.out.println("插入失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookCount> getAllBookCount() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		List<BookCount> bookCounts = null;
		try {
			String hql = "from BookCount";
			Query query = session.createQuery(hql);
			bookCounts = (List<BookCount>)query.list();
			ts.commit();
			session.close();
			return bookCounts;
		} catch (Exception e) {
			System.out.println("查询所有BookCount失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}

	@Override
	public boolean updateBookCount(BookCount bookCount) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		try {
			session.update(bookCount);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			System.out.println("更新BookCount信息失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return false;
		}
	}

}
