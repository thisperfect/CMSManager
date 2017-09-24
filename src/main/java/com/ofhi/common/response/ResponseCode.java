package com.ofhi.common.response;

public enum ResponseCode {
	/**
	 * 10000, "操作成功"
	 */
	success(10000, "操作成功"),
	/**
	 * 20000, "服务器错误"
	 */
	error(20000, "服务器错误"),
	/**
	 * 20001, "账户不存在"
	 */
	unknown_account(20001, "账户不存在"),
	/**
	 * 20002, "账户已禁用"
	 */
	forbidden_account(20002, "账户已禁用"),
	/***
	 * 20003, "密码错误"
	 */
	password_incorrect(20003, "密码错误"),
	/**
	 * 20004, "验证码错误"
	 */
	verify_captcha_error(20004, "验证码错误"),
	/**
	 * 20005, "无操作权限"
	 */
	unauthorized(20005, "无操作权限"),
	/**
	 * 20006, "该条记录无法编辑"
	 */
	can_not_edit(20006, "该条记录无法编辑"),
	/**
	 * 20007, "未登录"
	 */
	unauthenticated(20007, "未登录"),
	/**
	 * 20008, "非法请求"
	 */
	forbidden_ip(20008, "非法请求"),
	/**
	 * 30007, "接口请求超时"
	 */
	request_timeout(20009, "接口请求超时"),
	/**
	 * 30001, "参数格式错误"
	 */
	param_format_error(30001, "参数格式错误"),
	/**
	 * 30002, "缺少参数"
	 */
	missing_parameter(30002, "缺少参数"),
	/**
	 * 30003, "该名称已存在"
	 */
	name_already_exist(30003, "该名称已存在"),
	/**
	 * 30004, "该记录不存在"
	 */
	data_not_exist(30004, "该记录不存在"),
	/**
	 * 30005, "该登录名已存在"
	 */
	login_name_already_exist(30005, "该登录名已存在"),
	/**
	 * 30006, "该编码已存在"
	 */
	code_already_exist(30006, "该编码已存在"),
	/***
	 * 30007,"验签失败"
	 */
	sign_error(30007,"验签失败"),
	/**
	 * 30008,"请求过于频繁"
	 */
	request_frequently(30008,"请求过于频繁"),
	/**
	 * 30009,"账户未激活"
	 */
	account_not_activate(30009,"账户未激活");
	
	

	private int code;
	private String msg;

	ResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ResponseCode{" + "code=" + code + ", msg='" + msg + '\'' + '}';
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
}
