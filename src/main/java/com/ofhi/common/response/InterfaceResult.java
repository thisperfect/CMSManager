package com.ofhi.common.response;

import com.alibaba.fastjson.JSONObject;

public class InterfaceResult {

    private int code = ResponseCode.success.getCode();
    private String msg = ResponseCode.success.getMsg();
    private Object result;

    private InterfaceResult() {}
    private InterfaceResult(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public static InterfaceResult getSuccess() {
        return new InterfaceResult();
    }

    public static InterfaceResult getErrorResult() {
        return new InterfaceResult(ResponseCode.error.getCode(), ResponseCode.error.getMsg());
    }


    public static InterfaceResult getErrorResult(String errorMsg) {
        return new InterfaceResult(ResponseCode.error.getCode(), errorMsg);
    }

    public static InterfaceResult getErrorResult(int errorCode, String errorMsg) {
        return new InterfaceResult(errorCode, errorMsg);
     }

    public static InterfaceResult getErrorResult(ResponseCode code) {
        return new InterfaceResult(code.getCode(), code.getMsg());
    }

    @Override
    public String toString() {
        return String.format("{\"code\":\"%s\", \"msg\":\"%s\", \"result\":%s}", code, msg, result == null ? null : JSONObject.toJSONString(result));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
