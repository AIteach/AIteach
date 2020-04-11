package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.CommentLike;

public interface ICommentLikeService {

	public List<CommentLike> findall();

	public void deleteByUserIdAndCommentId(int userid, int commentid);

	public void save(CommentLike commentlike);

	public CommentLike findByUserIdAndCommentId(int userid, int commentid);

	public List<CommentLike> findByCommentId(int commentid);
}