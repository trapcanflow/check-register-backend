package com.njs.check.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chips
 * @date: 2020-02-09
 * @description:
 **/
public class ServerResponse {
    public static Map<String, Object> success() {
        return success(null);
    }

    public static Map<String, Object> success(Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("error_code", 0);
        res.put("data", data);
        return res;
    }

    public static Map<String, Object> success_excel_id(Object data, Integer excel_id) {
        Map<String, Object> res = new HashMap<>();
        res.put("error_code", 0);
        Map<String, Object> datas = new HashMap<>();
        datas.put("data", data);
        datas.put("excel_id", excel_id);
        res.put("data", datas);
        return res;
    }

    public static Map<String, Object> error(String errMsg) {
        Map<String, Object> res = new HashMap<>();
        res.put("error_code", 1);
        res.put("msg", errMsg
        );
        return res;
    }

    public static Map<String, Object> error() {
        Map<String, Object> res = new HashMap<>();
        res.put("error_code", 1);
        res.put("msg", ErrorRecord.ERROR_MSG.get());
        return res;
    }
}
