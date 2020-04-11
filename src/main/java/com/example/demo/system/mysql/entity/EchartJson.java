/**
	时间：2018年4月6日
	地点：
	包名：com.example.demo.system.entity
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EchartJson { //返回xml数组类
	@JSONField(format = "yyyy-MM-hh HH:mm")
	//private static String type="force";
	//private  String categories="[{ name: '学校'},{name: '课程'}";
	private  String type;
	private List<Name> categories;
	private List<Node> nodes;
	private List<Link> links;

	
}

