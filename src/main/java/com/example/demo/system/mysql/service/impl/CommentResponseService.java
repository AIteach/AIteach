package com.example.demo.system.mysql.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.CommentResponseJpaRepository;
import com.example.demo.system.mysql.entity.CommentResponse;
import com.example.demo.system.mysql.entity.CommentResponseAndName;
import com.example.demo.system.mysql.service.ICommentResponseService;

@Service("commentResponseService")
public class CommentResponseService implements ICommentResponseService {

	@Resource
	private CommentResponseJpaRepository commentResponseJpaRepository;
	@Override
	public List<CommentResponse> findAll() {
		// TODO Auto-generated method stub
		return this.commentResponseJpaRepository.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		this.commentResponseJpaRepository.deleteById(id);
		
	}

	@Override
	public void updateById(CommentResponse commentResponse) {
		// TODO Auto-generated method stub
		this.commentResponseJpaRepository.save(commentResponse);
		
	}

	@Override
	public CommentResponse save(CommentResponse commentResponse) {
		// TODO Auto-generated method stub
		return this.commentResponseJpaRepository.save(commentResponse);
	}

	@Override
	public Optional<CommentResponse> findCommentResponseById(int id) {
		// TODO Auto-generated method stub
		return this.commentResponseJpaRepository.findById(id);
	}
	
	@Override
	public List<CommentResponseAndName> findCommentResponseByCommentId(int commentid){
		return this.commentResponseJpaRepository.findCommentResponseByCommentId(commentid);	
	}

	@Override
	public Optional<CommentResponse> findById(int id) {
		// TODO Auto-generated method stub
		return this.commentResponseJpaRepository.findById(id);
	}
	

}
