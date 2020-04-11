package com.example.demo;

import com.example.demo.system.mysql.dao.CommentLikeJpaRepository;
import com.example.demo.system.mysql.entity.Comment;
import com.example.demo.system.mysql.entity.CommentLike;
import com.example.demo.system.mysql.entity.CommentResponse;
import com.example.demo.system.mysql.entity.CommentResponseAndName;
import com.example.demo.system.mysql.service.impl.CommentResponseService;
import com.example.demo.system.mysql.service.impl.CommentService;
import com.example.demo.system.mysql.service.impl.KgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrapApplicationTests {
	@Resource
	private KgService kgService;

	@Resource
	private CommentResponseService commentResponseService;

	@Resource
	private CommentService commentService;

	/*
	 * 下面为评论功能测试
	 */

	@Test
	public void commentdd() {
		// 测试评论
		Comment comment = new Comment();
		comment.setCommentContent("你的观点真的很好呀33");
		comment.setCtime(new Date());
		comment.setNodeId(2);
		comment.setUserid(2);
		//comment.setUsername("大哥");
		comment.setLikenum(100);
		this.commentService.save(comment);
	}

	@Test
	public void jcovjojiojj() {
		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setContent("你打球像菜虚鲲一样厉害！！");
		commentResponse.setCtime(new Date());
		commentResponse.setUserId(7);
		commentResponse.setResponseUserId(8);
		commentResponse.setCommentId(3);
		commentResponse.setLikenum(0);
		this.commentResponseService.save(commentResponse);
		
	}

	@Test
	public void liskjtkoj() {
		// 评论回复功能测试
		/*
		 * List<Comment> Comment =this.commentService.findAll(); for(Comment i:Comment)
		 * System.out.println(i);
		 */

		List<CommentResponseAndName> commentresponse = this.commentResponseService.findCommentResponseByCommentId(1);
		for (CommentResponseAndName i : commentresponse)
			System.out.println(i);
	}

	@Test
	public void tiamfj() {
		// 测试评论
		/*
		 * List<Comment> comment = this.commentService.findComentByNodeId(1);
		 * for(Comment i:comment) System.out.println(i);
		 */
		List<String> a=new ArrayList<String>() ;
		a.add("ddddd");
		a.add("fffff");
		for(String i :a)
			System.out.println(i);
		
	
	}
	
	
	@Test
	public void duojbiao() {
		List<CommentResponseAndName> a=this.commentResponseService.findCommentResponseByCommentId(2);
		for(CommentResponseAndName i:a)
			System.out.println(i);
	}
	
	
	@Resource
	private CommentLikeJpaRepository CommentLikeJpaRepository;

	@Test
	public void ddd() {
		for (int i = 1; i <= 6; i++) {
			CommentLike com = new CommentLike();
			com.setUserId(7);
			com.setCommentId(i);
			com.setCtime(new Date());
			this.CommentLikeJpaRepository.save(com);

		}
	}
	

}
