package com.wanguo.center_manager_sys.utils;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.exception.JsonParseException;

import java.lang.reflect.Type;

/**
 * 描述：
 *
 * @author Badguy
 */
public class ParamUtil {

    public static Object getFromJson(JSONObject jsonObject, String key, Type type) throws JsonParseException {
        try {
            if (jsonObject.get(key) == null) {
                throw new JsonParseException("can not find the key '" + key + "' in JSON Object");
            }
            return jsonObject.getObject(key, type);
        } catch (Exception e) {
            throw new JsonParseException("can not parse key '" + key + "' in JSON Object --> Exception : " + e.getMessage());
        }
    }

    public static Object getFromJsonWithDefault(JSONObject jsonObject, String key, Object de, Type type) throws JsonParseException {
        try {
            if (jsonObject.get(key) == null) {
                return de;
            }
            return jsonObject.getObject(key, type);
        } catch (Exception e) {
            throw new JsonParseException("can not parse key '" + key + "' in JSON Object --> Exception : " + e.getMessage());
        }
    }

}