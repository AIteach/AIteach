package com.example.demo.system.mysql.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.system.mysql.entity.Comment;
import com.example.demo.system.mysql.entity.CommentandName;

public interface ICommentService {
	
	
	public List<Comment> findAll();
	public void deleteById(int commentid);
	public void updateById(Comment comment);
	public Comment save(Comment comment);
	public CommentandName findCommentById(int id);
	public List<CommentandName> findComentByNodeId(int nodeit);
	
	public Optional<Comment> findById(int id);
	

}
