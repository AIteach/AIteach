/**
	时间：2018年4月4日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap-1
 * 
 */
package com.example.demo.system.mysql.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Administrator
 *
 */
@Entity(name = "kg")
public class Kg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kgId")
	private int kgId;
	private String kgName;
	private String kgRelation;
	private String kgDesc;
	private int nodeId;
	private int createrId;
	private String createrName;
	private int courseId;
	private int linkNum;
	private Date creatTime;
	private String kgUrl;
	private int kgParid;
	private int chapterId;

	public int getKgId() {
		return kgId;
	}

	public void setKgId(int kgId) {
		this.kgId = kgId;
	}

	public String getKgName() {
		return kgName;
	}

	public void setKgName(String kgName) {
		this.kgName = kgName;
	}

	public String getKgRelation() {
		return kgRelation;
	}

	public void setKgRelation(String kgRelation) {
		this.kgRelation = kgRelation;
	}

	public String getKgDesc() {
		return kgDesc;
	}

	public void setKgDesc(String kgDesc) {
		this.kgDesc = kgDesc;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getCreaterId() {
		return createrId;
	}

	public void setCreaterId(int createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getLinkNum() {
		return linkNum;
	}

	public void setLinkNum(int linkNum) {
		this.linkNum = linkNum;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getKgUrl() {
		return kgUrl;
	}

	public void setKgUrl(String kgUrl) {
		this.kgUrl = kgUrl;
	}

	public int getKgParid() {
		return kgParid;
	}

	public void setKgParid(int kgParid) {
		this.kgParid = kgParid;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	@Override
	public String toString() {
		return "Kg [kgId=" + kgId + ", kgName=" + kgName + ", kgRelation=" + kgRelation + ", kgDesc=" + kgDesc
				+ ", nodeId=" + nodeId + ", createrId=" + createrId + ", createrName=" + createrName + ", courseId="
				+ courseId + ", linkNum=" + linkNum + ", creatTime=" + creatTime + ", kgUrl=" + kgUrl + ", kgParid="
				+ kgParid + ", chapterid=" + chapterId + "]";
	}

}
