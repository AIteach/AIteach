package com.example.demo.classsearch;

import cn.hutool.http.HttpResponse;
import com.example.demo.classsearch.to.ClassVO;

import java.util.ArrayList;

/**
 * @author 84271
 */
public interface ClassSearch {
    /**
     * @param s         要搜索的内容
     * @param pageIndex 页数
     * @return
     */
    HttpResponse doSearch(String s, int pageIndex);

    /**
     * 根据doSearch返回的响应体，做出不同的处理
     *
     * @param httpResponse doSearch返回的HttpResponse响应体
     * @return
     */
    ArrayList<ClassVO> transform(HttpResponse httpResponse);
}
