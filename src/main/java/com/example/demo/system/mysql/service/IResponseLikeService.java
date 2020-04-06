package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.ResponseLike;

public interface IResponseLikeService {

	public List<ResponseLike> findall();

	public void deleteByUserIdAndCommentId(int userid, int commentid);

	public void save(ResponseLike commentlike);

	public ResponseLike findByUserIdAndCommentId(int userid, int commentid);

	public List<ResponseLike> findByCommentId(int commentid);
}