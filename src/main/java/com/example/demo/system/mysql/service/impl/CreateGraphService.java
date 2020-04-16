package com.example.demo.system.mysql.service.impl;


import com.example.demo.system.mysql.dao.CreateGraphJpaRepository;
import com.example.demo.system.mysql.entity.CreatGraph;
import com.example.demo.system.mysql.service.ICreateGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


/**
 * @author 84271
 */
@Service("createGraph")
public class CreateGraphService implements ICreateGraph {
	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private CreateGraphJpaRepository createGraphJpaRepository;
	@Override
	public List<CreatGraph> findOneBySql(String tablename, String filed, Object o){
		String sql =  "select * from " + tablename + " u WHERE u." + filed + "=?";
		Query query = entityManager.createNativeQuery(sql,CreatGraph.class);
		query.setParameter(1, o); 
		@SuppressWarnings("unchecked") 
		List<CreatGraph> list = query.getResultList();
		//System.out.println(list);
		entityManager.close();
		return list;
	}

	@Transactional
	@Modifying
	@Override
	public CreatGraph save(CreatGraph creatGraph) {
		return this.createGraphJpaRepository.save(creatGraph);
	}
	
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		 this.createGraphJpaRepository.deleteById(id);
	}

	public List<CreatGraph> findByContentOrMemberId(String content, int memberId) {
		// TODO Auto-generated method stub
		return this.createGraphJpaRepository.findByContentOrMemberId(content, memberId);
	} 
	
}
