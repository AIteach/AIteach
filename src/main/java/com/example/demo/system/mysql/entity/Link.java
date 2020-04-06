/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.entity;

/**
 * @author Administrator
 *
 */
public class Link { //连接类
	private int source;
	private int target;
	private String value;
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @param source
	 * @param target
	 * @param string 
	 */
	public Link(int source, int target) {
		super();
		this.source = source;
		this.target = target;
	}
	public Link(int source, int target, String value) {
		super();
		this.source = source;
		this.target = target;
		this.value= value;
	}
	
}

