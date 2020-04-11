/**
	时间：2018年3月28日
	地点：
	包名：com.example.demo.system.dao
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.system.mysql.entity.Member;

/**
 * @author Administrator
 *
 */

public interface MenberJpaRepository
		extends JpaRepository<Member, Integer>, JpaSpecificationExecutor<Member>, Serializable {

	@Query
	public Member findByUsernameAndPassword(String username, String password);

	@Query
	public List<Member> findByUsername(String username);

}
