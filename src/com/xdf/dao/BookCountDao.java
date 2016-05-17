package com.xdf.dao;

import java.util.List;

import com.xdf.dto.BookCount;

public interface BookCountDao {
	/**
	 * ����༶������������Ϣ
	 * @param book
	 * @return ������
	 */
	public boolean insertBookCount(BookCount book);
	
	/**
	 * ��ѯ����������Ϣ
	 * @return ������Ϣ�б� List<BookCount>
	 */
	public List<BookCount> getAllBookCount();
	
	/**
	 * ����������Ϣ
	 * @param bookCount
	 * @return ���½��
	 */
	public boolean updateBookCount(BookCount bookCount);
}	
