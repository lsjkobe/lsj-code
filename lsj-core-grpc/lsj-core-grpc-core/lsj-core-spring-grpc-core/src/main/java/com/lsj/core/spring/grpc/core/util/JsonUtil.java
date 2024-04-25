package com.lsj.core.spring.grpc.core.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JsonUtil.
 *
 * @author lishangj
 * @version v1.0.0
 * @date 2021-08-25 16:37
 */
public class JsonUtil {

    private static final Gson GSON = new Gson();

    private JsonUtil() {
    }

    public static String toJson(Object object) {
        return object != null ? GSON.toJson(object) : null;
    }

    public static String stringToJson(String key, String value) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            Map<String, String> map = new HashMap<>(16);
            map.put(key, value);
            return toJson(map);
        } else {
            return null;
        }
    }

    public static <T> String listToJson(List<T> ts) {
        return GSON.toJson(ts);
    }

    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        return !StringUtils.isEmpty(jsonStr) && clazz != null ? GSON.fromJson(jsonStr, clazz) : null;
    }

    public static Map<String, Object> toMap(String json) {
        //Gson写法
        Type type1 = new TypeToken<Map<String, Object>>(){}.getType();
        return StringUtils.isEmpty(json) ? null : GSON.fromJson(json, type1);
    }

    public static <T> List<T> toList(Object obj) {
        Type type = new TypeToken<List<T>>(){}.getType();
        String jsonStr = GSON.toJson(obj);
        return GSON.fromJson(jsonStr, type);
    }

    public static <T> List<T> toList(String jsonStr) {
        Type type = new TypeToken<List<T>>(){}.getType();
        return GSON.fromJson(jsonStr, type);
    }

    public static <T> T fromJson(String jsonString, Class<T> classOfT) {
        return GSON.fromJson(jsonString, classOfT);
    }

    public static void main(String[] args) {
        String json = "{\"name\":\"lsj test\"}";
        Test test = fromJson(json, Test.class);

    }

    public static class Test {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
