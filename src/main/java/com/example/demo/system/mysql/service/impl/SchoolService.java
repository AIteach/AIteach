/**
	时间：2018年4月6日
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

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.SchoolJpaRepository;
import com.example.demo.system.mysql.entity.School;
import com.example.demo.system.mysql.service.ISchoolService;

/**
 * @author Administrator
 *
 */
@Service("schoolService")
public class SchoolService implements ISchoolService {
	@Resource
	private SchoolJpaRepository schoolJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<School> findAll() {
		return schoolJpaRepository.findAll();
	}

	@Override
	public void deleteSchool(int schoolId) {
		schoolJpaRepository.deleteById(schoolId);
	}

	/**
	 * 方法名:com.example.demo.system.service.impl 文件名:findByid
	 */

	@Override
	public List<School> findOneBySql(String tablename, String filed, Object o) {
		// TODO Auto-generated method stub
		String sql = "select * from " + tablename + " u WHERE u." + filed + "=?";
		// "select * from " + tablename + " u WHERE u." + filed + "=?"
		System.out.println("sql语句：");
		System.out.println(sql);
		Query query = entityManager.createNativeQuery(sql, School.class);
		query.setParameter(1, o);
		@SuppressWarnings("unchecked")
		List<School> list = query.getResultList();
		// System.out.println(list);
		entityManager.close();
		return list;
	}

	/**
	 * 方法名:com.example.demo.system.service.impl 文件名:save
	 * 
	 * @return
	 */
	@Override
	@Modifying
	@CacheEvict(cacheNames = "schoolItem", allEntries = true)
	public int save(School school) {
		// System.out.println("校名"+school.getId());
		return this.schoolJpaRepository.save(school).getId();
	}

	@Override
	public boolean findByUserNameAndPassword() {
		// TODO Auto-generated method stub
		return false;
	}

	public School findBySchoolId(int id) {
		// TODO Auto-generated method stub
		return this.schoolJpaRepository.findByid(id);
	}

//	 public School findBySchoolId(int schoolId) {
//		 System.out.println("是我:jpa"+this.schoolJpaRepository.findByid(schoolId));	 		
//		 
//		 return null;
//		 
//	 }

}
