package com.example.demo.system.mysql.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.system.mysql.entity.CommentLike;

public interface CommentLikeJpaRepository extends JpaRepository<CommentLike, Integer> {
	@Query(value = "delete from CommentLike where userId =?1 and commentId =?2")
	@Modifying
	@Transactional
	public void deleteByUserIdAndCommentId(int userid, int commentid);

	@Query(value = "select c from CommentLike c where c.userId=?1 and c.commentId= ?2")
	public CommentLike findByUserIdAndCommentId(int userid, int commentid);

	@Query(value = "select c from CommentLike c where c.commentId= ?1")
	public List<CommentLike> findByCommentId(int commentid);
}
