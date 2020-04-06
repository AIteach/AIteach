package com.example.demo.system.mysql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.system.mysql.entity.CommentResponse;
import com.example.demo.system.mysql.entity.CommentResponseAndName;

public interface CommentResponseJpaRepository extends JpaRepository<CommentResponse, Integer> {

	@Query("select new com.example.demo.system.mysql.entity.CommentResponseAndName(c,m1.name,m2.name) from CommentResponse c,member m1,member m2 where c.commentId=?1 and m1.id=c.userId and m2.id=c.responseUserId order by c.ctime asc")
	public List<CommentResponseAndName> findCommentResponseByCommentId(int commentid);

}
