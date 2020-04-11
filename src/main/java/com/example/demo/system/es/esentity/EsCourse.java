/**
	时间：2018年4月4日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap-1
 * 
 */
package com.example.demo.system.es.esentity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.example.demo.system.mysql.entity.Course;

/**
 * @author Administrator
 *
 */
@Data
@ToString
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

	public void change(Course course) {
		this.courseId = course.getCourseId();
		this.courseDesc = course.getCourseDesc();
		this.courseName = course.getCourseName();
		this.nodeId = course.getNodeId();
		this.createrId = course.getCreaterId();
		this.schoolId = course.getSchoolId();
	}
}
