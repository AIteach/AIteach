package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


/**
 * @author 84271
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="create_graph")
public class CreatGraph {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "categories", length =6000)
	private String categories;
	@Column(name = "links", length =6000)
	private String links;
	@Column(name = "nodes",length=6000)
	private String nodes;	
	private String content;
	private Date creatTime; 
	private String title;
	
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;

	public void setCreatTime() {
		this.creatTime = new Date();
	}

	public CreatGraph(Member member, String categories, String links, String nodes) {
		this.id = id;
		this.member=member;
		this.categories = categories;
		this.links = links;
		this.nodes = nodes;
		this.setCreatTime();
	}
	

	public CreatGraph( int id,String categories, String links, String nodes, String content,String title, Member member) {
		super();
		this.id = id;
		this.categories = categories;
		this.links = links;
		this.nodes = nodes;
		this.content = content;
		this.setCreatTime();
		this.title = title;
		this.member = member;
	}
	public CreatGraph(String categories, String links, String nodes, String content,String title, Member member) {
		super();
		this.id = id;
		this.categories = categories;
		this.links = links;
		this.nodes = nodes;
		this.content = content;
		this.setCreatTime();
		this.title = title;
		this.member = member;
	}
	/**
	 * @param id
	 * @param member
	 * @param categories
	 * @param links
	 * @param nodes
	 * @param content
	 */
	public CreatGraph(int id, Member member, String categories, String links, String nodes, String content) {
		super();
		this.categories = categories;
		this.links = links;
		this.nodes = nodes;
		this.content = content;
	}
	
	
	
}
