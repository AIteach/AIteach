package com.example.demo.system.mysql.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.system.mysql.entity.ResponseLike;

public interface ResponseJpaRepository extends JpaRepository<ResponseLike, Integer> {
	@Query(value = "delete from ResponseLike where userId =?1 and commentId =?2")
	@Modifying
	@Transactional
	public void deleteByUserIdAndCommentId(int userid, int commentid);

	@Query(value = "select r from ResponseLike r where r.userId=?1 and r.commentId= ?2")
	public ResponseLike findByUserIdAndCommentId(int userid, int commentid);

	@Query(value = "select r from ResponseLike r where r.commentId= ?1")
	public List<ResponseLike> findByCommentId(int commentid);

}
