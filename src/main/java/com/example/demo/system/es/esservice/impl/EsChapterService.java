package com.example.demo.system.es.esservice.impl;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.demo.system.es.esdao.EsChapterJpa;
import com.example.demo.system.es.esentity.EsChapter;
import com.example.demo.system.es.esservice.EsIChapterService;

@Service
public class EsChapterService implements EsIChapterService {

	@Resource
	private EsChapterJpa eschapterjpa;

	@Override
	public EsChapter save(EsChapter item) {
		// TODO Auto-generated method stub
		return eschapterjpa.save(item);
	}

	@Override
	public void delete(EsChapter item) {
		// TODO Auto-generated method stub
		eschapterjpa.delete(item);

	}

	@Override
	public Iterable<EsChapter> findall() {
		// TODO Auto-generated method stub
		return eschapterjpa.findAll();
	}

	@Override
	public Iterable<EsChapter> findall(Sort sort) {
		// TODO Auto-generated method stub
		return eschapterjpa.findAll(sort);
	}

	@Override
	public Optional<EsChapter> findone(Integer id) {
		// TODO Auto-generated method stub
		return eschapterjpa.findById(id);
	}

	@Override
	public Page<EsChapter> search(SearchQuery search) {
		// TODO Auto-generated method stub
		return eschapterjpa.search(search);
	}

}
