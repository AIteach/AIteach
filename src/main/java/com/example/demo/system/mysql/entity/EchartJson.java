/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Administrator
 *
 */

public class EchartJson { //返回xml数组类
	@JSONField(format = "yyyy-MM-hh HH:mm")
	//private static String type="force";
	//private  String categories="[{ name: '学校'},{name: '课程'}";
	private  String type;
	private List<Name> categories;
	private List<Node> nodes;
	private List<Link> links;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Name> getCategories() {
		return categories;
	}
	public void setCategories(List<Name> names) {
		this.categories = names;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes2) {
		this.nodes = nodes2;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	@Override
	public String toString() {
		return "EchartJson [type=" + type + ", categories=" + categories + ", nodes=" + nodes + ", links=" + links
				+ "]";
	}
	
	
}

