/**
	时间：2018年4月18日
	地点：
	包名：com.example.demo.system.dao
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.system.mysql.entity.Source;

/**
 * @author Administrator
 *
 */
public interface SourceJpaRepository extends JpaRepository<Source, Integer> {
	
}
