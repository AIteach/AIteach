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

import com.example.demo.system.mysql.entity.Kg;

/**
 * @author Administrator
 *
 */
@Document(indexName = "kg")
public class EsKg {
	@Id
	private int kgId;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String kgName;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String kgDesc;
	private int nodeId;
	private int createrId;
	private int courseId;
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

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	@Override
	public String toString() {
		return "EsKg [kgId=" + kgId + ", kgName=" + kgName + ", kgDesc=" + kgDesc + ", nodeId=" + nodeId
				+ ", createrId=" + createrId + ", courseId=" + courseId + ", chapterId=" + chapterId + "]";
	}

	public void change(Kg kg) {
		this.chapterId = kg.getChapterId();
		this.courseId = kg.getCourseId();
		this.createrId = kg.getCreaterId();
		this.kgDesc = kg.getKgDesc();
		this.kgId = kg.getKgId();
		this.kgName = kg.getKgName();
		this.nodeId = kg.getNodeId();
	}

}
