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

import com.example.demo.system.mysql.entity.Kg;

/**
 * @author Administrator
 *
 */
@Data
@ToString
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
