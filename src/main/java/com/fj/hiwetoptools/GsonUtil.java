package com.fj.hiwetoptools;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public final class GsonUtil {
	public static final Gson gson = new Gson();
	
	public static Object json2List(String json,Type t){
		return gson.fromJson(json, t);
	}
	
	public static<T> T json2Obj(String json,Class<T> t ){
		return gson.fromJson(json, t);
	}
	
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
	
    public static <T> List<T> gsonToList(String json, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }
}
