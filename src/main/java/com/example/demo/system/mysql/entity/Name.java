/**
	时间：2018年4月7日
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
public class Name {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param id
	 * @param name
	 */
	public Name(String name) {
		this.name = name;
	}

}
