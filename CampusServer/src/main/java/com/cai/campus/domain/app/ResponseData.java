package com.cai.campus.domain.app;

/**
 * 统一返回数据格式
 */
public class ResponseData {
    String msg;
    int statusCode;
    Object data;

    public ResponseData(String msg, int statusCode, Object data) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.data = data;
    }

    public ResponseData(String msg , int statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
        this.data = null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
