package com.example.demo.system.mysql.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.system.mysql.entity.CommentResponse;
import com.example.demo.system.mysql.entity.CommentResponseAndName;

public interface ICommentResponseService {

	public List<CommentResponse> findAll();

	public void deleteById(int id);

	public void updateById(CommentResponse commentResponse);

	public CommentResponse save(CommentResponse commentResponse);

	public Optional<CommentResponse> findCommentResponseById(int id);

	public List<CommentResponseAndName> findCommentResponseByCommentId(int commentid);

	public Optional<CommentResponse> findById(int id);

}
