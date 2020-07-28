package com.example.demo.classsearch.to;

/**
 * @program: moocsearch
 * @ClassName: Techar
 * @Description:
 * @Author: 842712494@qq.com
 * @Date: 2020/7/20 13:55
 * @Version: 1.0.0
 */
public class ClassTeacher {

    public ClassTeacher() {

    }

    public ClassTeacher(String techarRealName, String lectorTitle) {
        this.techarRealName = techarRealName;
        this.lectorTitle = lectorTitle;
    }

    /**
     * @Description: 教师名字
     */
    String techarRealName;
    /**
     * @Description: 教师职称
     */
    String lectorTitle;


    @Override
    public String toString() {
        return "Teacher{" +
                "techarRealName='" + techarRealName + '\'' +
                ", lectorTitle='" + lectorTitle + '\'' +
                '}';
    }

    public String getTecharRealName() {
        return techarRealName;
    }

    public void setTecharRealName(String techarRealName) {
        this.techarRealName = techarRealName;
    }

    public String getLectorTitle() {
        return lectorTitle;
    }

    public void setLectorTitle(String lectorTitle) {
        this.lectorTitle = lectorTitle;
    }
}
