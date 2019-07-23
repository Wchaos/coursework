package com.wangchao.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一数据返回格式
 *
 * @author wangchao
 * @date 2019/2/21
 */
public class ReturnMsg {

    public static Map<String, Object> successMsg() {
        Map<String, Object> resultMap = new HashMap<String, Object>(4);
        resultMap.put("code", 200);
        resultMap.put("message", "success");
        resultMap.put("result", true);
        return resultMap;
    }

    public static Map<String, Object> successMsg(String result) {
        Map<String, Object> resultMap = new HashMap<String, Object>(4);
        resultMap.put("code", 200);
        resultMap.put("message", "success");
        resultMap.put("result", result);
        return resultMap;
    }

    public static Map<String, Object> failedMsg() {
        Map<String, Object> resultMap = new HashMap<String, Object>(4);
        resultMap.put("code", 201);
        resultMap.put("message", "failed");
        resultMap.put("result", false);
        return resultMap;
    }

    public static Map<String, Object> failedMsg(String msg) {
        Map<String, Object> resultMap = new HashMap<String, Object>(4);
        resultMap.put("code", 201);
        resultMap.put("message", msg);
        resultMap.put("result", false);
        return resultMap;
    }



}
