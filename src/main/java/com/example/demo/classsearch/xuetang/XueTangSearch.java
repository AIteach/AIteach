package com.example.demo.classsearch.xuetang;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.classsearch.ClassSearch;
import com.example.demo.classsearch.to.ClassTeacher;
import com.example.demo.classsearch.to.ClassVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: XueTangSearch
 * @ClassName: XueTangSearch
 * @Description: https://next.xuetangx.com/
 * 学堂在线课程搜索服务
 * @Author: 842712494@qq.com
 * @Date: 2020/7/20 11:56
 * @Version: 1.0.0
 */
public class XueTangSearch implements ClassSearch {

    private static String SEARCHURL = "https://next.xuetangx.com/api/v1/lms/get_product_list/?page=";
    private static String COURSEURL = "https://next.xuetangx.com/course/";

    /**
     * @param s         搜索内容
     * @param pageIndex 页数
     * @return 返回HttpResponse响应体
     */
    @Override
    public HttpResponse doSearch(String s, int pageIndex) {
        HttpRequest httpRequest = HttpRequest.post(SEARCHURL + pageIndex);
        //Referer: https://next.xuetangx.com/search?query=%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F&page=1
        httpRequest.header("Connection", "keep-alive");
        httpRequest.header("Content-Length", "97");
        httpRequest.header("django-language", "zh");
        httpRequest.header("xtbz", "xt");
        httpRequest.header("Accept-Language", "zh");
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Accept", "application/json, text/plain, */*");
        httpRequest.header("Sec-Fetch-Dest", "empty");
        httpRequest.header("x-client", "web");
        httpRequest.header("Origin", "https://next.xuetangx.com");
        httpRequest.header("Sec-Fetch-Site", "same-origin");
        httpRequest.header("Sec-Fetch-Mode", "cors");
        httpRequest.header("Accept-Encoding", "gzip, deflate, br");
        JSONObject json = createForm(s, pageIndex, 20);
        httpRequest.body(json.toString());
        HttpResponse httpResponse = httpRequest.execute();
        return httpResponse;
    }

    @Override
    public ArrayList<ClassVO> transform(HttpResponse httpResponse) {
        JSONObject jsonObject = JSONUtil.parseObj(httpResponse.body());
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("product_list");
        ArrayList<ClassVO> list = new ArrayList<>();
        if (jsonArray != null && jsonArray.size() != 0) {
            jsonArray.forEach(e -> list.add(transformClass((JSONObject) e)));
        }
        return list;
    }

    /**
     * @param keyword   要搜索的关键字
     * @param pageIndex 分页数据
     * @param pageSize  页大小
     * @return 返回post的请求体
     */
    private JSONObject createForm(String keyword, int pageIndex, int pageSize) {
        JSONObject json = new JSONObject();
        json.putOpt("query", keyword);
//        json.putOpt("chief_org", new JSONArray());
//        json.putOpt("classify", new JSONArray());
//        json.putOpt("selling_type", new JSONArray());
        json.putOpt("status", 2);
        json.putOpt("appid", 10000);
        return json;
    }

    private ClassVO transformClass(JSONObject json) {
        ClassVO classVO = new ClassVO();
        //课程地址
        String url = json.getStr("sign");
        classVO.setCourseUrl(COURSEURL + url);

        //课程名称
        String name = json.getStr("name");
        classVO.setCourseName(name);
        //课程简介
        String short_intro = json.getStr("short_intro");
        classVO.setCourseContent(short_intro);

        //教师列表
        List<ClassTeacher> teachers = json.getJSONArray("teacher").stream().map(item -> {
            String teacher = ((JSONObject) item).getStr("name");
            String job_title = ((JSONObject) item).getStr("job_title");
            return new ClassTeacher(teacher, job_title);
        }).collect(Collectors.toList());
        classVO.setTeacherList(teachers);
        return classVO;
    }

    public static void main(String[] args) {
        XueTangSearch XueTangSearch = new XueTangSearch();
        HttpResponse httpResponse = XueTangSearch.doSearch("计算机网络", 2);
        System.out.println(XueTangSearch.transform(httpResponse));
    }

}
