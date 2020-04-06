package com.example.demo.system.mysql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.system.mysql.entity.Comment;
import com.example.demo.system.mysql.entity.CommentandName;

public interface CommentJpaRepository extends JpaRepository<Comment, Integer> {

	@Query(value = "select new com.example.demo.system.mysql.entity.CommentandName(c,m.name)from Comment c,member m where c.nodeId=?1 and m.id=c.userid order by c.ctime asc")
	public List<CommentandName> findCommentByNodeId(int nodeid);

	@Query(value = "select new com.example.demo.system.mysql.entity.CommentandName(c,m.name)from Comment c,member m where c.id=?1 and m.id=c.userid")
	public CommentandName findCommentById(int id);
}
