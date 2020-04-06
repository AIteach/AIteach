/**
	时间：2018年4月9日
	地点：
	包名：com.example.demo.system.service
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.Teacher;

/**
 * @author Administrator
 *
 */
public interface ITeacherService {
	public List<Teacher> findAll();
	public List<Teacher> findOneBySql(String tablename, String filed, Object o);
	public void deleteBySchoolId(String tablename, String field, Object o);
}
