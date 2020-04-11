package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
//添加 user_id 和 comment_id 的约束
public class CommentLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // id

	private int userId; // 用户id
	private int commentId; // 评论id
	private Date ctime; // 点赞时间

}
