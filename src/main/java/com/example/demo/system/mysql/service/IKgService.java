/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.service.impl
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.Kg;

/**
 * @author Administrator
 *
 */
public interface IKgService {
	public List<Kg> findAll();

	public List<Kg> findOneBySql(String tablename, String filed, Object o);

	public Kg save(Kg kg);

	/**
	 * 方法名:com.example.demo.system.service 文件名:updateByEntiy
	 * 
	 * @return
	 */
	void updateByEntiy(Kg kg);
}
