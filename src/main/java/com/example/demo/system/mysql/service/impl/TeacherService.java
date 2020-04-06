/**
	时间：2018年4月9日
	地点：
	包名：com.example.demo.system.service.impl
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.TeacherJpaRepository;
import com.example.demo.system.mysql.entity.Teacher;
import com.example.demo.system.mysql.service.ITeacherService;

/**
 * @author Administrator
 *
 */
@Service("teacherService")
public class TeacherService implements ITeacherService {
	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private TeacherJpaRepository teacherJpaRepository;

	@Override
	public List<Teacher> findAll() {

		return teacherJpaRepository.findAll();
	}

	@Override
	public List<Teacher> findOneBySql(String tablename, String filed, Object o) {
		String sql = "select * from " + tablename + " u WHERE u." + filed + "=?";
		// "select * from " + tablename + " u WHERE u." + filed + "=?"
		System.out.println("sql语句：");
		System.out.println(sql);
		Query query = entityManager.createNativeQuery(sql, Teacher.class);
		query.setParameter(1, o);
		@SuppressWarnings("unchecked")
		List<Teacher> list = query.getResultList();
		System.out.println(list);
		entityManager.close();
		return list;
	}

	/**
	 * 方法名:com.example.demo.system.service.impl 文件名:deleteById
	 */
	@Override
	public void deleteBySchoolId(String tablename, String field, Object o) {
		// TODO Auto-generated method stub
		String sql = "select * from " + tablename + " u WHERE u." + field + "=?";
		// "select * from " + tablename + " u WHERE u." + filed + "=?"
		System.out.println("sql语句：");
		System.out.println(sql);
		Query query = entityManager.createNativeQuery(sql, Teacher.class);
		// query.setParameter(1, o);
		@SuppressWarnings("unchecked")
		List<Teacher> list = query.getResultList();
		System.out.println(list);
		entityManager.close();
	}

}
