/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Administrator
 *
 */
@Entity(name="linking")
public class Linking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int preId;
	private int rearId;
	private String relationship;
	private Date creatime;
	private int type; //0为关系.1为信息
	private String ResourceURl;
	private String value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPreId() {
		return preId;
	}
	public void setPreId(int preId) {
		this.preId = preId;
	}
	public int getRearId() {
		return rearId;
	}
	public void setRearId(int rearId) {
		this.rearId = rearId;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public Date getCreatime() {
		return creatime;
	}
	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getResourceURl() {
		return ResourceURl;
	}
	public void setResourceURl(String resourceURl) {
		ResourceURl = resourceURl;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @param id
	 * @param preId
	 * @param rearId
	 * @param relationship
	 * @param creatime
	 * @param type
	 * @param resourceURl
	 */
//	public Linking(int id, int preId, int rearId, String relationship, Date creatime, int type, String resourceURl) {
//		super();
//		this.id = id;
//		this.preId = preId;
//		this.rearId = rearId;
//		this.relationship = relationship;
//		this.creatime = creatime;
//		this.type = type;
//		ResourceURl = resourceURl;
//	}
	/**
	 * @param preId
	 * @param rearId
	 * @param type
	 * @param resourceURl
	 */
	public Linking() {
		
	
	}
	public Linking(int preId, int rearId,int type) {
		super();
		this.preId = preId;
		this.rearId = rearId;
		this.type= type;
	
	}
	
	public Linking(int preId, int rearId,int type,String value) {
		super();
		this.preId = preId;
		this.rearId = rearId;
		this.type= type;
		this.value=value;
		this.creatime=new Date();
	
	}

	@Override
	public String toString() {
		return "Linking{" +
				"id=" + id +
				", preId=" + preId +
				", rearId=" + rearId +
				", relationship='" + relationship + '\'' +
				", creatime=" + creatime +
				", type=" + type +
				", ResourceURl='" + ResourceURl + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
