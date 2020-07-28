package com.example.demo.classsearch;

import cn.hutool.http.HttpResponse;
import com.example.demo.classsearch.mooc.MoocSearch;
import com.example.demo.classsearch.to.ClassVO;
import com.example.demo.classsearch.xuetang.XueTangSearch;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: AIteach
 * @ClassName: ClassSearchService
 * @Description:
 * @Author: 842712494@qq.com
 * @Date: 2020/7/28 22:08
 * @Version: 1.0.0
 */
@Service
public class ClassSearchService {

    @Resource
    private ThreadPoolExecutor executor;


    public ArrayList<ClassVO> getClassFromSearch(String name) throws ExecutionException, InterruptedException {
        Future<ArrayList<ClassVO>> moocsearch = executor.submit(() -> {
            MoocSearch moocSearch = new MoocSearch();
            HttpResponse httpResponse = moocSearch.doSearch(name, 1);
            ArrayList<ClassVO> transform = moocSearch.transform(httpResponse);
            return transform;
        });
        Future<ArrayList<ClassVO>> xuetangSearch = executor.submit(() -> {
            XueTangSearch XueTangSearch = new XueTangSearch();
            HttpResponse httpResponse = XueTangSearch.doSearch(name, 1);
            ArrayList<ClassVO> transform = XueTangSearch.transform(httpResponse);
            return transform;
        });

        ArrayList<ClassVO> classVOS = moocsearch.get();
        ArrayList<ClassVO> classVOS1 = xuetangSearch.get();
        classVOS.addAll(classVOS1);
        return classVOS;
    }

}
