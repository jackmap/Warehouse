package com.example.controller.sys.common;

import java.util.List;

public class JsonResult {

    private Integer code;
    private Integer count;
    private String message;
    private List data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public static JsonResult Error() {
        return new JsonResult(1,"查询失败");
    }

    public JsonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult(Integer code, List data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public JsonResult(Integer code, Integer count, List data, String message) {
        this.code = code;
        this.count = count;
        this.data = data;
        this.message = message;
    }
}
