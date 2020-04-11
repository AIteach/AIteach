/**
	时间：2018年4月7日
	地点：
	包名：com.example.demo.system.dao
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.system.mysql.entity.Node;

/**
 * @author Administrator
 *
 */
public interface NodeJpaRepository extends JpaRepository<Node, Integer> {

	List<Node> findByCategoryGreaterThanEqual(int i);

	List<Node> findAllByNameLike(String name);


}
