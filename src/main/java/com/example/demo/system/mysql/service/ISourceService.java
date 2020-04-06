/**
	时间：2018年4月18日
	地点：
	包名：com.example.demo.system.service
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.Source;

/**
 * @author Administrator
 *
 */
public interface ISourceService {

	/**
	 * 方法名:com.example.demo.system.service
		文件名:uploadFile
	 */
	void uploadFile(byte[] file, String filePath, String fileName) throws Exception;

	/**
	 * 方法名:com.example.demo.system.service
		文件名:save
	 * @return 
	 */
	public Source save(Source source);

	/**
	 * 方法名:com.example.demo.system.service
		文件名:findOneBySql
	 */
	List<Source> findOneBySql(String tablename, String filed, Object o);

	/**
	 * 方法名:com.example.demo.system.service
		文件名:updateSourceBySourceId
	 */
	Source updateSourceBySourceId(Source source);

}
