/**
	时间：2018年4月18日
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
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.SourceJpaRepository;
import com.example.demo.system.mysql.entity.Source;
import com.example.demo.system.mysql.service.ISourceService;

/**
 * @author Administrator
 *
 */
@Service(value = "sourceService")
public class SourceService implements ISourceService {
	@Resource
	private SourceJpaRepository sourceJpaRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 方法名:com.example.demo.system.service.impl 文件名:save
	 */
	@Override
	public Source save(Source source) {
		return this.sourceJpaRepository.save(source);
		// TODO Auto-generated method stub

	}

	/**
	 * 方法名:com.example.demo.system.service.impl 文件名:findOneBySql
	 */

	@Override
	@Transactional
	@Modifying
	public Source updateSourceBySourceId(Source source) {
		return this.sourceJpaRepository.save(source);
	}

	@Override
	public List<Source> findOneBySql(String tablename, String filed, Object o) {
		// TODO Auto-generated method stub
		String sql = "select * from " + tablename + " u WHERE u." + filed + "=?";
		Query query = entityManager.createNativeQuery(sql, Source.class);
		query.setParameter(1, o);
		@SuppressWarnings("unchecked")
		List<Source> list = query.getResultList();
		entityManager.close();
		return list;
	}

	public List<Source> findAll() {
		// TODO Auto-generated method stub
		return this.sourceJpaRepository.findAll();
	}

}
