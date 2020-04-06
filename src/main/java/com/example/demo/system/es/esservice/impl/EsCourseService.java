package com.example.demo.system.es.esservice.impl;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.demo.system.es.esdao.EsCourseJpa;
import com.example.demo.system.es.esentity.EsCourse;
import com.example.demo.system.es.esservice.EsICourseService;

@Service
public class EsCourseService implements EsICourseService {

	@Resource
	private EsCourseJpa escoursejpa;

	@Override
	public EsCourse save(EsCourse item) {
		// TODO Auto-generated method stub
		return escoursejpa.save(item);
	}

	@Override
	public void delete(EsCourse item) {
		// TODO Auto-generated method stub
		escoursejpa.delete(item);
	}

	@Override
	public Iterable<EsCourse> findall() {
		// TODO Auto-generated method stub
		return escoursejpa.findAll();
	}

	@Override
	public Iterable<EsCourse> findall(Sort sort) {
		// TODO Auto-generated method stub
		return escoursejpa.findAll(sort);
	}

	@Override
	public Optional<EsCourse> findone(Integer id) {
		// TODO Auto-generated method stub
		return escoursejpa.findById(id);
	}

	@Override
	public Page<EsCourse> search(SearchQuery search) {
		// TODO Auto-generated method stub
		return escoursejpa.search(search);
	}

}
