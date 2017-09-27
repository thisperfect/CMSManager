package com.ofhi.common.response;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

public class InterfaceResult {

    @Getter
    @Setter
    private int code = ResponseCode.success.getCode();

    @Getter
    @Setter
    private String msg = ResponseCode.success.getMsg();

    @Getter
    @Setter
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

}
