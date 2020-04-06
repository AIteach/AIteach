package com.example.demo.system.es.esservice.impl;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.demo.system.es.esdao.EsSchoolJpa;
import com.example.demo.system.es.esentity.EsSchool;
import com.example.demo.system.es.esservice.EsISchoolService;

@Service
public class EsSchoolService implements EsISchoolService {

	@Resource
	private EsSchoolJpa esschooljpa;

	@Override
	public EsSchool save(EsSchool item) {
		// TODO Auto-generated method stub
		return esschooljpa.save(item);
	}

	@Override
	public void delete(EsSchool item) {
		// TODO Auto-generated method stub
		esschooljpa.delete(item);
	}

	@Override
	public Iterable<EsSchool> findall() {
		// TODO Auto-generated method stub
		return esschooljpa.findAll();
	}

	@Override
	public Iterable<EsSchool> findall(Sort sort) {
		// TODO Auto-generated method stub
		return esschooljpa.findAll(sort);
	}

	@Override
	public Optional<EsSchool> findone(Integer id) {
		// TODO Auto-generated method stub
		return esschooljpa.findById(id);
	}

	@Override
	public Page<EsSchool> search(SearchQuery search) {
		// TODO Auto-generated method stub
		return esschooljpa.search(search);
	}

}
