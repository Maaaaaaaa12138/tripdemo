package com.tripdemo.response;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class ResData {
    public static String getRes(String mes, Object obj){
        Map<String, Object> map = new HashMap<>();
        map.put("mes", mes);
        map.put("data", obj);
        return JSON.toJSONString(map);
    }
}
