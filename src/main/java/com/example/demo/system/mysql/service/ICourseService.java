/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.service
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.Course;

/**
 * @author Administrator
 *
 */
public interface ICourseService {

	/**
	 * 方法名:com.example.demo.system.service 文件名:findAll
	 */
	public List<Course> findAll();

	public List<Course> findOneBySql(String tablename, String filed, Object o);

	public Course save(Course course);

	void deleteById(int courseId);

	void updateById(Course course);
	// public List<Course> kgNodeId(String tablename,String filed, Object o);

}
