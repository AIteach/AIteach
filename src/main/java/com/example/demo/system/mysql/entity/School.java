/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Administrator
 *
 */
@Entity(name = "school")
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int nodeId;
	private int createrId;
	private String createrName;
	private String schoolName;
	private String schoolDesc;
	private int score; // 学校分值
	private int linkNum; // 实体数
	private int teacherNum;// 教师数
	private String province;
	private String city;
	private int locatedX;
	private int locatedY;
	private String imgUrl;

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolDesc() {
		return schoolDesc;
	}

	public void setSchoolDesc(String schoolDesc) {
		this.schoolDesc = schoolDesc;
	}

	public int getLinkNum() {
		return linkNum;
	}

	public void setLinkNum(int linkNum) {
		this.linkNum = linkNum;
	}

	private int courseNum;// 课程数
	private int StuNum;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	public int getStuNum() {
		return StuNum;
	}

	public void setStuNum(int stuNum) {
		StuNum = stuNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(int teacherNum) {
		this.teacherNum = teacherNum;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getLocatedX() {
		return locatedX;
	}

	public void setLocatedX(int locatedX) {
		this.locatedX = locatedX;
	}

	public int getLocatedY() {
		return locatedY;
	}

	public void setLocatedY(int locatedY) {
		this.locatedY = locatedY;
	}

	/**
	 * @param id
	 * @param nodeId
	 * @param schoolName
	 * @param city
	 */
	public School() {

	}

	public School(String schoolName) {
		super();
		this.schoolName = schoolName;
	}

}
