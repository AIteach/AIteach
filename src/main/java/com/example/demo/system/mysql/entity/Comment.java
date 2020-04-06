package com.example.demo.system.mysql.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 评论id
	private int nodeId; // 节点id
	private int userid; // 此评论所属用户id
	// private String username; //此评论所属用户名
	private String commentContent; // 评论内容
	private Date ctime; // 评论发表时间
	private int likenum;// 点赞数
	private double positive; // 积极性系数
	private double nagetive; // 消极

	public double getPositive() {
		return positive;
	}

	public double getNagetive() {
		return nagetive;
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

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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
		return "Comment [id=" + id + ", nodeId=" + nodeId + ", userid=" + userid + ", commentContent=" + commentContent
				+ ", ctime=" + ctime + ", likenum=" + likenum + ", positive=" + positive + ", nagetive=" + nagetive
				+ "]";
	}

	public void SetPositiveAndNagetive() {
		CalPandN cal = new CalPandN();
		double[] res = cal.getPositiveAndNagetive(getCommentContent());
		this.positive = res[0];
		this.nagetive = res[1];
	}

}
