package com.lemon.response;

import java.util.HashMap;

/**
 * common return
 */
public class R extends HashMap<String, Object> {

    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = 1;
    public static final int UNAUTHORIZED_CODE = 401;
    public static final int FORBIDDEN_CODE = 403;
    public static final R OK = new R(SUCCESS_CODE, "操作成功");
    public static final R FAIL = new R(FAIL_CODE, "操作失败");
    public static final R UNAUTHORIZED = new R(UNAUTHORIZED_CODE, "未授权");
    public static final R FORBIDDEN = new R(FORBIDDEN_CODE, "拒绝访问");

    public R() {
    }

    public R(int code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public static R ok() {
        return ok("操作成功");
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("code", SUCCESS_CODE);
        r.put("msg", msg);
        return r;
    }

    public static R error() {
        return error("操作失败");
    }

    public static R error(String msg) {
        R r = new R();
        r.put("code", FAIL_CODE);
        r.put("msg", msg);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
