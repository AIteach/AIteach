package com.example.demo.system.mysql.entity;

import java.util.ArrayList;
import java.util.List;

public class CommentandName {
	private Comment comment;
	private String username;
	private List<CommentResponseAndName> commentresponse = new ArrayList<CommentResponseAndName>();

	public CommentandName() {
	}

	public List<CommentResponseAndName> getCommentresponse() {
		return this.commentresponse;
	}

	public void setCommentresponse(List<CommentResponseAndName> commentresponse) {
		this.commentresponse = commentresponse;
	}

	public CommentandName(Comment comment) {
		this.comment = comment;
		this.username = null;
	}

	public CommentandName(Comment comment, String username) {
		this.comment = comment;
		this.username = username;
	}

	public String toString() {
		return "CommentandName [comment=" + this.comment + ", username=" + this.username + ", commentresponse="
				+ this.commentresponse + "]";
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
