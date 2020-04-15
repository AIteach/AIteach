package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @program: AIteach
 * @ClassName: JsonUtils
 * @Description: json工具类
 * @Author: 842712494@qq.com
 * @Date: 2020/4/11 22:50
 * @Version: 1.0.0
 */
public class JsonUtils {

    public static JSONObject getEsMessage(String JpaBeanName, Object object) {
        JSONObject json = new JSONObject();
        //传入DAO接口名称
        json.put("JpaBeanClassName", JpaBeanName);
        //将对象转成json字符串
        json.put("Object", object);
        return json;
    }

}
