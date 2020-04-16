package com.example.demo.system.mysql.dao;


import com.example.demo.system.mysql.entity.CreatGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 84271
 */
public interface CreateGraphJpaRepository extends JpaRepository<CreatGraph, Integer> {

	@Query
	List<CreatGraph> findByContentOrMemberId(String content, int memberId);

}
