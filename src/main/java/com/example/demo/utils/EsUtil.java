package com.example.demo.utils;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

public class EsUtil {
    public static SearchQuery getSearchQuery(String name, Object text) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(name, text);
        return new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
    }
}
