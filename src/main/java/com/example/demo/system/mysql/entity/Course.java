/**
	时间：2018年4月4日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap-1
 * 
 */
package com.example.demo.system.mysql.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Administrator
 *
 */
@Entity(name ="course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="course_id")
	private int courseId;
	private String courseName;
	private String courseDesc;
	private int nodeId;
	private int courseScore;
	private int courseEvaluate;
	 @OneToOne(cascade={CascadeType.ALL})
     @JoinColumn(name="teacher_teacher_id")//关联的表为address表，其主键是id
	private Teacher teacher;
	private int schoolId;
	private int createrId;
	private String createrName;
	private Date creatTime;
	private int stuNum;
	private int LinkNum;
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public int getLinkNum() {
		return LinkNum;
	}
	public void setLinkNum(int linkNum) {
		LinkNum = linkNum;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public int getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(int courseScore) {
		this.courseScore = courseScore;
	}
	public int getCourseEvaluate() {
		return courseEvaluate;
	}
	public void setCourseEvaluate(int courseEvaluate) {
		this.courseEvaluate = courseEvaluate;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String coureseName) {
		this.courseName = coureseName;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String coureseDesc) {
		this.courseDesc = coureseDesc;
	}
	
	
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
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
	
	/**
	 * @param courseId
	 * @param courseName
	 * @param courseDesc
	 * @param schoolId
	 * @param url
	 */


	
}
