package com.example.demo;

import com.example.demo.system.mysql.dao.CommentJpaRepository;
import com.example.demo.system.mysql.dao.CommentLikeJpaRepository;
import com.example.demo.system.mysql.dao.CommentResponseJpaRepository;
import com.example.demo.system.mysql.dao.ResponseJpaRepository;
import com.example.demo.system.mysql.entity.CommentLike;
import com.example.demo.system.mysql.entity.CommentResponse;
import com.example.demo.system.mysql.entity.ResponseLike;
import com.example.demo.system.mysql.service.impl.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentLikeTest {
	@Resource
	private CommentLikeJpaRepository CommentLikeJpaRepository;

	@Resource
	private CommentJpaRepository commentJpaRepository;

	@Resource
	private CommentResponseJpaRepository CommentResponseJpaRepository;

	@Resource
	private ResponseJpaRepository ResponseJpaRepository;

	@Test
	public void gett() {
		List<CommentResponse> a = this.CommentResponseJpaRepository.findAll();
		for (CommentResponse i : a) {
			i.SetPositiveAndNagetive();
			System.out.println(i);
			this.CommentResponseJpaRepository.save(i);
		}
	}

	@Test
	@Transactional
	@Rollback(value = false)
	public void ddd() {
		for (int i = 1; i <= 6; i++) {
			CommentLike com = new CommentLike();
			com.setUserId(7);
			com.setCommentId(i);
			com.setCtime(new Date());
			this.CommentLikeJpaRepository.deleteByUserIdAndCommentId(7, i);
		}
	}

	@Test
	public void fff() {
		for (int i = 2; i <= 10; i++) {
			ResponseLike com = new ResponseLike();
			com.setUserId(7);
			com.setCommentId(i);
			com.setCtime(new Date());
			this.ResponseJpaRepository.save(com);
		}
	}

	@Resource
	private CommentService commentservice;

	@Test
	public void fjjife() {
		List<ResponseLike> com = this.ResponseJpaRepository.findAll();
		for (ResponseLike i : com) {
			System.out.println(i);
		}
	}

}
