package com.example.demo.system.mysql.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
//添加 user_id 和 comment_id 的约束
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "comment_id" }) })
public class ResponseLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // id

	@Column(name = "user_id")
	private int userId; // 用户id
	@Column(name = "comment_id")
	private int commentId; // 回复id
	private Date ctime; // 点赞时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "CommentLike [id=" + id + ", userId=" + userId + ", commentId=" + commentId + ", ctime=" + ctime + "]";
	}
}
