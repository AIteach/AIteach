package com.example.demo.system.es.esservice.impl;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.demo.system.es.esdao.EsCommentJpa;
import com.example.demo.system.es.esentity.EsComment;
import com.example.demo.system.es.esservice.EsICommentService;

@Service
public class EsCommentService implements EsICommentService {

	@Resource
	private EsCommentJpa commentesjpa;

	@Override
	public EsComment save(EsComment item) {
		// TODO Auto-generated method stub
		return commentesjpa.save(item);
	}

	@Override
	public void delete(EsComment item) {
		// TODO Auto-generated method stub
		commentesjpa.delete(item);

	}

	@Override
	public Iterable<EsComment> findall() {
		// TODO Auto-generated method stub
		return commentesjpa.findAll();
	}

	@Override
	public Iterable<EsComment> findall(Sort sort) {
		// TODO Auto-generated method stub
		return commentesjpa.findAll(sort);
	}

	@Override
	public Optional<EsComment> findone(Integer id) {
		// TODO Auto-generated method stub
		return commentesjpa.findById(id);
	}

	@Override
	public Iterable<EsComment> findByCtimeBetween(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return commentesjpa.findByCtimeBetween(date1, date2);
	}

	public Page<EsComment> search(SearchQuery search) {
		// TODO Auto-generated method stub
		return commentesjpa.search(search);
	}

}
