package com.ofhi.common.exception;

import com.ofhi.common.response.ResponseCode;

public class RequestErrorException extends RuntimeException {

	private int errCode;
	private String errMsg;

	public int getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public RequestErrorException(String message) {
		super(message);
	}

	public RequestErrorException() {
		super();
	}

	public RequestErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestErrorException(Throwable cause) {
		super(cause);
	}

	public RequestErrorException(int errCode, String errMsg) {
		super(String.format("%s : %s", errCode, errMsg));
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public RequestErrorException(ResponseCode code) {
		super(String.format("%s : %s", code.getCode(), code.getMsg()));
		this.errCode = code.getCode();
		this.errMsg = code.getMsg();
	}

}
