package com.xdf.dao;

import java.util.List;

import com.xdf.dto.BookCount;

public interface BookCountDao {
	/**
	 * 插入班级编码配送量信息
	 * @param book
	 * @return 插入结果
	 */
	public boolean insertBookCount(BookCount book);
	
	/**
	 * 查询所有配送信息
	 * @return 配送信息列表 List<BookCount>
	 */
	public List<BookCount> getAllBookCount();
	
	/**
	 * 更新配送信息
	 * @param bookCount
	 * @return 更新结果
	 */
	public boolean updateBookCount(BookCount bookCount);
}	
