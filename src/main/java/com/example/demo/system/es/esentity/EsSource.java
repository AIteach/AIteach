/**
	时间：2018年4月18日
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

import com.example.demo.system.mysql.entity.Source;

/**
 * @author Administrator
 *
 */
@Document(indexName = "source")
public class EsSource {
	@Id
	private int id;
	private int kgId;
	private int nodeId;
	private int createrId;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String sourceName;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String sourceDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKgId() {
		return kgId;
	}

	public void setKgId(int kgId) {
		this.kgId = kgId;
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

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceDesc() {
		return sourceDesc;
	}

	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}

	@Override
	public String toString() {
		return "EsSource [id=" + id + ", kgId=" + kgId + ", nodeId=" + nodeId + ", createrId=" + createrId
				+ ", sourceName=" + sourceName + ", sourceDesc=" + sourceDesc + "]";
	}

	public void change(Source source) {
		this.createrId = source.getCreaterId();
		this.id = source.getId();
		this.kgId = source.getKgId();
		this.nodeId = source.getNodeId();
		this.sourceDesc = source.getSourceDesc();
		this.sourceName = source.getSourceName();
	}

}
