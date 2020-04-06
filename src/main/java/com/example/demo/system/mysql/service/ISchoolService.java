/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.service
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.School;

/**
 * @author Administrator
 *
 */
public interface ISchoolService {
	public List<School> findAll();
	public void deleteSchool(int schoolId);
	public List<School> findOneBySql(String tablename, String filed, Object o);
	public int save(School school);
	boolean findByUserNameAndPassword();
}
