package com.example.demo.system.mysql.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chapter {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chapterId;
	private String chapterName;
	private int courseId;
	private Date createTime;
	private int nodeId;
	private int createrId;
	private String createrName;
	private String chapterDesc;
	private int linkNum;
	private String chapterRelation;

	public String getChapterRelation() {
		return chapterRelation;
	}
	public void setChapterRelation(String chapterRelation) {
		this.chapterRelation = chapterRelation;
	}
	public int getLinkNum() {
		return linkNum;
	}
	public void setLinkNum(int linkNum) {
		this.linkNum = linkNum;
	}
	public String getChapterDesc() {
		return chapterDesc;
	}
	public void setChapterDesc(String chapterDesc) {
		this.chapterDesc = chapterDesc;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public int getCreaterId() {
		return createrId;
	}
	public void setCreaterId(int creater) {
		this.createrId = creater;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public int getNodeId() {
		return nodeId;
	}
	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}


	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Date getCreatTime() {
		return createTime;
	}

	public void setCreatTime(Date creatTime) {
		this.createTime = creatTime;
	}
	@Override
	public String toString() {
		return "Chapter [chapterId=" + chapterId + ", chapterName=" + chapterName + ", courseId=" + courseId
				+ ", createTime=" + createTime + ", nodeId=" + nodeId + ", createrId=" + createrId + ", createrName="
				+ createrName + ", chapterDesc=" + chapterDesc + ", linkNum=" + linkNum + ", chapterRelation="
				+ chapterRelation + "]";
	}
	

}
