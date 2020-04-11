package com.example.demo.system.mysql.service;


import java.util.List;

import com.example.demo.system.mysql.entity.Chapter;
public interface IChapterService {

	public List<Chapter> findAll() ;
	public List<Chapter> findOneBySql(String tablename, String filed, Object o);
	public Chapter save(Chapter chapter);
	/**
	 * 方法名:com.example.demo.system.service
		文件名:updateByEntiy
	 * @return 
	 */
	void updateByEntiy(Chapter chapter);
}
