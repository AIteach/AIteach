package com.example.demo.system.mysql.service;


import com.example.demo.system.mysql.entity.CreatGraph;

import java.util.List;

/**
 * @author 84271
 */
public interface ICreateGraph {

	List<CreatGraph> findOneBySql(String tablename, String filed, Object o);

	CreatGraph save(CreatGraph creatGraph);

	void deleteById(int id);

}
