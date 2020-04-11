package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Chapter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chapterId;
	private String chapterName;
	private int courseId;
	private Date createTime;
	private int nodeId;
	private int createrId;
	private String createrName;
	private String chapterDesc;
	private int linkNum;
	private String chapterRelation;

}
