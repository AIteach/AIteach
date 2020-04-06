package com.example.demo.system.mysql.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "CommentResponse")
public class CommentResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 评论id
	private int commentId; // 评论id
	private int userId; // 回复评论的id
	private int responseUserId; // 被回复评论的id
	private String Content; // 回复内容
	private Date ctime; // 回复发表时间
	private int likenum; // 点赞数
	private double positive; // 积极性系数
	private double nagetive; // 消极

	public double getPositive() {
		return positive;
	}

	

	public double getNagetive() {
		return nagetive;
	}

	public void SetPositiveAndNagetive() {
		CalPandN cal = new CalPandN();
		double[] res = cal.getPositiveAndNagetive(getContent());
		this.positive = res[0];
		this.nagetive = res[1];
	}

	public void addLikeNum() {
		this.likenum++;
	}

	public void subLikeNum() {
		this.likenum--;
		if (this.likenum < 0)
			this.likenum = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getResponseUserId() {
		return responseUserId;
	}

	public void setResponseUserId(int responseUserId) {
		this.responseUserId = responseUserId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public int getLikenum() {
		return likenum;
	}

	public void setLikenum(int likenum) {
		this.likenum = likenum;
	}

	@Override
	public String toString() {
		return "CommentResponse [id=" + id + ", commentId=" + commentId + ", userId=" + userId + ", responseUserId="
				+ responseUserId + ", Content=" + Content + ", ctime=" + ctime + ", likenum=" + likenum + ", positive="
				+ positive + ", nagetive=" + nagetive + "]";
	}

}
