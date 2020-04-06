/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap
 * 
 */
package com.example.demo.system.es.esentity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.example.demo.system.mysql.entity.School;

/**
 * @author Administrator
 *
 */
@Document(indexName = "school")
public class EsSchool {
	@Id
	private int id;
	private int nodeId;
	private int createrId;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String schoolName;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String schoolDesc;

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

	public int getCreaterId() {
		return createrId;
	}

	public void setCreaterId(int createrId) {
		this.createrId = createrId;
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

	@Override
	public String toString() {
		return "EsSchool [id=" + id + ", nodeId=" + nodeId + ", createrId=" + createrId + ", schoolName=" + schoolName
				+ ", schoolDesc=" + schoolDesc + "]";
	}

	public void change(School school) {
		this.createrId = school.getCreaterId();
		this.id = school.getId();
		this.nodeId = school.getNodeId();
		this.schoolDesc = school.getSchoolDesc();
		this.schoolName = school.getSchoolName();
	}

}
