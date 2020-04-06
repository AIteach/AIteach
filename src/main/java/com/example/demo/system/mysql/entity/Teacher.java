/**
	时间：2018年4月5日
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
@Entity(name = "teacher")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacherId")
	private int teacherId;
	private String teacherName;
	private String teacherDesc;
	private Date creatime;

	public int teacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherDesc() {
		return teacherDesc;
	}

	public void setTeacherDesc(String teacherDesc) {
		this.teacherDesc = teacherDesc;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Date getCreatime() {
		return creatime;
	}

	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}

}