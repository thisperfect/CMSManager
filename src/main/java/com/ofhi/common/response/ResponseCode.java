package com.ofhi.common.response;

public enum ResponseCode {
	/**
	 * 10000, "操作成功"
	 */
	success(10000, "\\u64cd\\u4f5c\\u6210\\u529f"),
	/**
	 * 20000, "服务器错误"
	 */
	error(20000, "\\u670d\\u52a1\\u5668\\u9519\\u8bef"),
	/**
	 * 20001, "账户不存在"
	 */
	unknown_account(20001, "\\u8d26\\u6237\\u4e0d\\u5b58\\u5728"),
	/**
	 * 20002, "账户已禁用"
	 */
	forbidden_account(20002, "\\u8d26\\u6237\\u5df2\\u7981\\u7528"),
	/***
	 * 20003, "密码错误"
	 */
	password_incorrect(20003, "\\u5bc6\\u7801\\u9519\\u8bef"),
	/**
	 * 20004, "验证码错误"
	 */
	verify_captcha_error(20004, "\\u9a8c\\u8bc1\\u7801\\u9519\\u8bef"),
	/**
	 * 20005, "无操作权限"
	 */
	unauthorized(20005, "\\u65e0\\u64cd\\u4f5c\\u6743\\u9650"),
	/**
	 * 20006, "该条记录无法编辑"
	 */
	can_not_edit(20006, "\\u8be5\\u6761\\u8bb0\\u5f55\\u65e0\\u6cd5\\u7f16\\u8f91"),
	/**
	 * 20007, "未登录"
	 */
	unauthenticated(20007, "\\u672a\\u767b\\u5f55"),
	/**
	 * 20008, "非法请求"
	 */
	forbidden_ip(20008, "\\u975e\\u6cd5\\u8bf7\\u6c42"),
	/**
	 * 30007, "接口请求超时"
	 */
	request_timeout(20009, "\\u63a5\\u53e3\\u8bf7\\u6c42\\u8d85\\u65f6"),
	/**
	 * 30001, "参数格式错误"
	 */
	param_format_error(30001, "\\u53c2\\u6570\\u683c\\u5f0f\\u9519\\u8bef"),
	/**
	 * 30002, "缺少参数"
	 */
	missing_parameter(30002, "\\u7f3a\\u5c11\\u53c2\\u6570"),
	/**
	 * 30003, "该名称已存在"
	 */
	name_already_exist(30003, "\\u8be5\\u540d\\u79f0\\u5df2\\u5b58\\u5728"),
	/**
	 * 30004, "该记录不存在"
	 */
	data_not_exist(30004, "\\u8be5\\u8bb0\\u5f55\\u4e0d\\u5b58\\u5728"),
	/**
	 * 30005, "该登录名已存在"
	 */
	login_name_already_exist(30005, "\\u8be5\\u767b\\u5f55\\u540d\\u5df2\\u5b58\\u5728"),
	/**
	 * 30006, "该编码已存在"
	 */
	code_already_exist(30006, "\\u8be5\\u7f16\\u7801\\u5df2\\u5b58\\u5728"),
	/***
	 * 30007,"验签失败"
	 */
	sign_error(30007,"\\u9a8c\\u7b7e\\u5931\\u8d25"),
	/**
	 * 30008,"请求过于频繁"
	 */
	request_frequently(30008,"\\u8bf7\\u6c42\\u8fc7\\u4e8e\\u9891\\u7e41"),
	/**
	 * 30009,"账户未激活"
	 */
	account_not_activate(30009,"\\u8d26\\u6237\\u672a\\u6fc0\\u6d3b");
	
	

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
