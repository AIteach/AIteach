package com.example.demo.system.es.esentity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.example.demo.system.mysql.entity.Chapter;

@Document(indexName = "chapter")
public class EsChapter {
	@Id
	private int chapterId;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String chapterName;
	private int courseId;

	private int nodeId;
	private int createrId;

	private String chapterDesc;

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
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

	public String getChapterDesc() {
		return chapterDesc;
	}

	public void setChapterDesc(String chapterDesc) {
		this.chapterDesc = chapterDesc;
	}

	@Override
	public String toString() {
		return "EsChapter [chapterId=" + chapterId + ", chapterName=" + chapterName + ", courseId=" + courseId
				+ ", nodeId=" + nodeId + ", createrId=" + createrId + ", chapterDesc=" + chapterDesc + "]";
	}

	public void change(Chapter chapter) {
		this.chapterDesc = chapter.getChapterDesc();
		this.chapterId = chapter.getChapterId();
		this.chapterName = chapter.getChapterName();
		this.courseId = chapter.getCourseId();
		this.createrId = chapter.getCreaterId();
		this.nodeId = chapter.getNodeId();
	}
}
