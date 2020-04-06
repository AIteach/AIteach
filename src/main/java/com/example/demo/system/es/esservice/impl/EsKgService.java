package com.example.demo.system.es.esservice.impl;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.demo.system.es.esdao.EsKgJpa;
import com.example.demo.system.es.esentity.EsKg;
import com.example.demo.system.es.esservice.EsIKgService;

@Service
public class EsKgService implements EsIKgService {

	@Resource
	private EsKgJpa eskgjpa;

	@Override
	public EsKg save(EsKg item) {
		// TODO Auto-generated method stub
		return eskgjpa.save(item);
	}

	@Override
	public void delete(EsKg item) {
		// TODO Auto-generated method stub
		eskgjpa.delete(item);
	}

	@Override
	public Iterable<EsKg> findall() {
		// TODO Auto-generated method stub
		return eskgjpa.findAll();
	}

	@Override
	public Iterable<EsKg> findall(Sort sort) {
		// TODO Auto-generated method stub
		return eskgjpa.findAll(sort);
	}

	@Override
	public Optional<EsKg> findone(Integer id) {
		// TODO Auto-generated method stub
		return eskgjpa.findById(id);
	}

	@Override
	public Page<EsKg> search(SearchQuery search) {
		// TODO Auto-generated method stub
		return eskgjpa.search(search);
	}

}
