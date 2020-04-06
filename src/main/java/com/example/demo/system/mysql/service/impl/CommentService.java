package com.example.demo.system.mysql.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.CommentJpaRepository;
import com.example.demo.system.mysql.entity.Comment;
import com.example.demo.system.mysql.entity.CommentandName;
import com.example.demo.system.mysql.service.ICommentService;

@Service("commentService")
public class CommentService implements ICommentService {

	@Resource
	private CommentJpaRepository commentJpaRepository;

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return this.commentJpaRepository.findAll();
	}

	@Override
	public void deleteById(int commentid) {
		this.commentJpaRepository.deleteById(commentid);
		// TODO Auto-generated method stub
	}

	@Override
	public void updateById(Comment comment) {
		// TODO Auto-generated method stub
		this.commentJpaRepository.save(comment);
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return this.commentJpaRepository.save(comment);
	}

	@Override
	public CommentandName findCommentById(int id) {
		// TODO Auto-generated method stub
		return this.commentJpaRepository.findCommentById(id);
	}

	@Override
	public List<CommentandName> findComentByNodeId(int nodeid) {

		return this.commentJpaRepository.findCommentByNodeId(nodeid);
	}

	@Override
	public Optional<Comment> findById(int id) {
		// TODO Auto-generated method stub
		return this.commentJpaRepository.findById(id);
	}

}
