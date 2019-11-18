package com.idiotalex.model;

import java.util.HashMap;

/**
 * Created by idiot on 2018/8/8.
 */
public class R extends HashMap<String, Object> {

    // 返回错误
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    // 成功
    public static R ok(String msg) {
        R r = new R();
        r.put("code", 0);
        r.put("msg", msg);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
