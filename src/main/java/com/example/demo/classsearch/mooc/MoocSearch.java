package com.example.demo.classsearch.mooc;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.classsearch.ClassSearch;
import com.example.demo.classsearch.to.ClassTeacher;
import com.example.demo.classsearch.to.ClassVO;


import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: moocsearch
 * @ClassName: MoocSearch
 * @Description: https://www.icourse163.org/
 * 中国大学MOOC课程搜索服务
 * @Author: 842712494@qq.com
 * @Date: 2020/7/20 11:56
 * @Version: 1.0.0
 */
public class MoocSearch implements ClassSearch {

    private static final String URL = "https://www.icourse163.org/";
    private static List<HttpCookie> cookies;
    private static String SEARCHURL = "https://www.icourse163.org/web/j/mocSearchBean.searchCourse.rpc?csrfKey=";
    private static String CLASSURL = "https://www.icourse163.org/course/";

    private static int pageSize = 5;


    static {
        HttpResponse execute = HttpRequest.get(URL).execute();
        cookies = execute.getCookies();
        SEARCHURL += execute.getCookie("NTESSTUDYSI").getValue();
    }

    /**
     * @param s         搜索内容
     * @param pageIndex 页数
     * @return 返回HttpResponse响应体
     */
    @Override
    public HttpResponse doSearch(String s, int pageIndex) {
        HttpRequest httpRequest = HttpRequest.post(SEARCHURL);
        cookies.forEach(e -> httpRequest.cookie(e));
        JSONObject json = createForm(s, pageIndex, pageSize);
        httpRequest.form("mocCourseQueryVo", json);
        HttpResponse httpResponse = httpRequest.execute();
        return httpResponse;
    }


    @Override
    public ArrayList<ClassVO> transform(HttpResponse httpResponse) {
        JSONObject jsonObject = JSONUtil.parseObj(httpResponse.body());
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("list");
        ArrayList<ClassVO> list = new ArrayList<>();
        if (jsonArray != null && jsonArray.size() != 0) {
            jsonArray.forEach(e -> list.add(transformClass((JSONObject) e)));
        }
        return list;
    }

    private ClassVO transformClass(JSONObject json) {
        ClassVO classVO = new ClassVO();
        json.getJSONObject("mocCourseCard").getJSONObject("mocCourseCardDto");
        String courseId = json.getStr("courseId");
        JSONObject jsonObject1 = json.getJSONObject("mocCourseCard").getJSONObject("mocCourseCardDto");
        //课程名称
        String name = jsonObject1.getStr("name");
        classVO.setCourseName(name);

        //学校简称
        String shortName = jsonObject1.getJSONObject("schoolPanel").getStr("shortName");
        //课程简介
        String jsonContent = jsonObject1.getJSONObject("termPanel").getStr("jsonContent");
        classVO.setCourseContent(jsonContent);

        JSONArray teacherNameList = jsonObject1.getJSONObject("termPanel").getJSONArray("lectorPanels");
        //教师列表
        List<ClassTeacher> teachers = teacherNameList.stream().map((item) -> {
            String realName = ((JSONObject) item).getStr("realName");
            String lectorTitle = ((JSONObject) item).getStr("lectorTitle");
            return new ClassTeacher(realName, lectorTitle);
        }).collect(Collectors.toList());
        classVO.setTeacherList(teachers);
        classVO.setCourseUrl(CLASSURL + shortName + "-" + courseId);
        return classVO;
    }

    /**
     * @param keyword   要搜索的关键字
     * @param pageIndex 分页数据
     * @param pageSize  页大小
     * @return 返回post的请求体
     */
    private JSONObject createForm(String keyword, int pageIndex, int pageSize) {
        JSONObject json = new JSONObject();
        json.putOpt("keyword", keyword);
        json.putOpt("pageIndex", pageIndex);
        json.putOpt("highlight", false);
        json.putOpt("orderBy", 0);
        json.putOpt("stats", 30);
        json.putOpt("pageSize", pageSize);
        return json;
    }


    public static void main(String[] args) {
        MoocSearch moocSearch = new MoocSearch();
        HttpResponse httpResponse = moocSearch.doSearch("音乐", 1);
        ArrayList<ClassVO> transform = moocSearch.transform(httpResponse);
        System.out.println(transform);
    }

}
