package com.example.demo.system.mysql.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.ResponseJpaRepository;
import com.example.demo.system.mysql.entity.ResponseLike;
import com.example.demo.system.mysql.service.IResponseLikeService;

@Service("ResponseLikeService")
public class ResponseLikeService implements IResponseLikeService {

	@Resource
	private ResponseJpaRepository ResponseJpaRepository;

	@Override
	public List<ResponseLike> findall() {
		return this.ResponseJpaRepository.findAll();
	}

	@Override
	public void deleteByUserIdAndCommentId(int userid, int commentid) {
		this.ResponseJpaRepository.deleteByUserIdAndCommentId(userid, commentid);
	}

	@Override
	public void save(ResponseLike commentlike) {
		// TODO Auto-generated method stub
		this.ResponseJpaRepository.save(commentlike);
	}

	@Override
	public ResponseLike findByUserIdAndCommentId(int userid, int commentid) {
		// TODO Auto-generated method stub
		return this.ResponseJpaRepository.findByUserIdAndCommentId(userid, commentid);
	}

	@Override
	public List<ResponseLike> findByCommentId(int commentid) {
		// TODO Auto-generated method stub
		return this.findByCommentId(commentid);
	}
}
