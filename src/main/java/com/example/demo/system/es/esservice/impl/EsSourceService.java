package com.example.demo.system.es.esservice.impl;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.demo.system.es.esdao.EsSourceJpa;
import com.example.demo.system.es.esentity.EsSource;
import com.example.demo.system.es.esservice.EsISourceService;

@Service
public class EsSourceService implements EsISourceService {

	@Resource
	private EsSourceJpa essourcejpa;

	@Override
	public EsSource save(EsSource item) {
		// TODO Auto-generated method stub
		return essourcejpa.save(item);
	}

	@Override
	public void delete(EsSource item) {
		// TODO Auto-generated method stub
		essourcejpa.delete(item);
	}

	@Override
	public Iterable<EsSource> findall() {
		// TODO Auto-generated method stub
		return essourcejpa.findAll();
	}

	@Override
	public Iterable<EsSource> findall(Sort sort) {
		// TODO Auto-generated method stub
		return essourcejpa.findAll(sort);
	}

	@Override
	public Optional<EsSource> findone(Integer id) {
		// TODO Auto-generated method stub
		return essourcejpa.findById(id);
	}

	@Override
	public Page<EsSource> search(SearchQuery search) {
		// TODO Auto-generated method stub
		return essourcejpa.search(search);
	}

}
