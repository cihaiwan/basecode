package com.codezjsos.base.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * Created by zhufang on 2017/2/28.
 */
public class JsonUtils {
    private static Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static Map<String,Object> fromJson(String json){
       return gson.fromJson(json,Map.class);
    }
}
