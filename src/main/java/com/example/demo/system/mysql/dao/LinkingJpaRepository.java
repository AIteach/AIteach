/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.dao
 * 项目名：grap
 */
package com.example.demo.system.mysql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.system.mysql.entity.Linking;

import java.util.List;

/**
 * @author Administrator
 */
public interface LinkingJpaRepository extends JpaRepository<Linking, Integer> {

    /**
     * 方法名:com.example.demo.system.dao 文件名:findByPreIdOrReerId
     */
////	@Query("delete from Linking u where u.pre_id=?1 Or u.rear_id=?2")
////	@Modifying
////	@Transactional
//	void deleteByPreIdOrReerId(int preId, int rearId);

    List<Linking> findAllByPreIdOrRearId(Integer PreId,Integer RearId);

    // List<Linking> findAllByRearId(Integer integer);
}
