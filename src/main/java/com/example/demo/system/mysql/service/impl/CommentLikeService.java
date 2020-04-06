package com.example.demo.system.mysql.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.CommentLikeJpaRepository;
import com.example.demo.system.mysql.entity.CommentLike;
import com.example.demo.system.mysql.service.ICommentLikeService;

@Service("commentLikeService")
public class CommentLikeService implements ICommentLikeService {

	@Resource
	private CommentLikeJpaRepository commentlikeJpaRepository;

	@Override
	public List<CommentLike> findall() {
		return this.commentlikeJpaRepository.findAll();
	}

	@Override
	public void deleteByUserIdAndCommentId(int userid, int commentid) {
		this.commentlikeJpaRepository.deleteByUserIdAndCommentId(userid, commentid);
	}

	@Override
	public void save(CommentLike commentlike) {
		// TODO Auto-generated method stub
		this.commentlikeJpaRepository.save(commentlike);
	}

	@Override
	public CommentLike findByUserIdAndCommentId(int userid, int commentid) {
		// TODO Auto-generated method stub
		return this.commentlikeJpaRepository.findByUserIdAndCommentId(userid, commentid);
	}

	@Override
	public List<CommentLike> findByCommentId(int commentid) {
		// TODO Auto-generated method stub
		return this.findByCommentId(commentid);
	}
}
