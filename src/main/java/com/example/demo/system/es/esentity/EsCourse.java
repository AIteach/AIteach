/**
	时间：2018年4月4日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap-1
 * 
 */
package com.example.demo.system.es.esentity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.example.demo.system.mysql.entity.Course;

/**
 * @author Administrator
 *
 */
@Document(indexName = "course")
public class EsCourse {
	@Id
	private int courseId;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String courseName;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String courseDesc;
	private int nodeId;
	private int schoolId;
	private int createrId;

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getCreaterId() {
		return createrId;
	}

	public void setCreaterId(int createrId) {
		this.createrId = createrId;
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

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return "EsCourse [courseId=" + courseId + ", courseName=" + courseName + ", courseDesc=" + courseDesc
				+ ", nodeId=" + nodeId + ", schoolId=" + schoolId + ", createrId=" + createrId + "]";
	}

	public void change(Course course) {
		this.courseId = course.getCourseId();
		this.courseDesc = course.getCourseDesc();
		this.courseName = course.getCourseName();
		this.nodeId = course.getNodeId();
		this.createrId = course.getCreaterId();
		this.schoolId = course.getSchoolId();
	}
}
