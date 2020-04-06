package com.example.demo.system.es.esentity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.example.demo.system.mysql.entity.Comment;

@Document(indexName = "comment")
public class EsComment {
	@Id
	private int id; // 评论id

	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String commentContent; // 评论内容
	@Field(type = FieldType.Text)
	private Date ctime; // 评论发表时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "EsComment [id=" + id + ", commentContent=" + commentContent + ", ctime=" + ctime + "]";
	}

	public void change(Comment comment) {
		this.id = comment.getId();
		this.ctime = comment.getCtime();
		this.commentContent = comment.getCommentContent();
	}

}
