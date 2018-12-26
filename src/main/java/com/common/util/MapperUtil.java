package com.common.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MapperUtil {

	public static <T> T MapperTo(Object obj, Class<T> classz) {
		String tempStr = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
		return JSON.parseObject(tempStr, classz);
	}

	public static <T> List<T> MapperToArray(Object obj, Class<T> classz) {
		String tempStr = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
		return JSON.parseArray(tempStr, classz);
	}
}
