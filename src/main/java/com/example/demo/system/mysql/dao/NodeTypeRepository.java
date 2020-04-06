/**
	时间：2018年4月7日
	地点：
	包名：com.example.demo.system.dao
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.system.mysql.entity.NodeType;

/**
 * @author Administrator
 *
 */
public interface NodeTypeRepository extends JpaRepository<NodeType, Integer> {

}
