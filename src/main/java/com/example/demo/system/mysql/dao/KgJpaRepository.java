/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.dao
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.system.mysql.entity.Kg;

/**
 * @author Administrator
 *
 */
public interface KgJpaRepository extends JpaRepository<Kg, Integer> {

}
