package com.ofhi.common.user;

import com.ofhi.common.response.ResponseCode;
import com.ofhi.common.util.StringHelper;
import com.ofhi.modules.cms.sys.entity.vo.UserLogin;
import com.ofhi.modules.cms.sys.entity.vo.UserRegister;
import com.ofhi.common.response.InterfaceResult;
import com.ofhi.common.security.MD5;
import org.apache.commons.lang3.StringUtils;

public class UserHelper {

	public static InterfaceResult checkLoginParams(UserLogin userLogin, String sign, Long timestamp) {
		InterfaceResult result = InterfaceResult.getSuccess();
		if (userLogin == null) {
			return InterfaceResult.getErrorResult(ResponseCode.missing_parameter);
		} else if (timestamp == null || StringUtils.isEmpty(sign) || StringUtils.isEmpty(userLogin.getUsername()) || StringUtils.isEmpty(userLogin.getPassword())) {
			return InterfaceResult.getErrorResult(ResponseCode.missing_parameter);
		} else if (comparingTimestamps(timestamp) > 1) {
			return InterfaceResult.getErrorResult(ResponseCode.request_timeout);
		} else if (StringUtils.isEmpty(userLogin.getUsername()) || StringUtils.isEmpty(userLogin.getPassword())) {
			return InterfaceResult.getErrorResult(ResponseCode.missing_parameter);
		} else if (!StringUtils.equals(sign, sign(userLogin.getUsername(), userLogin.getPassword(), timestamp))) {
			return InterfaceResult.getErrorResult(ResponseCode.sign_error);
		} 
		return result;
	}

	public static InterfaceResult checkRegisterParams(UserRegister userRegister, String sign, Long timestamp) {
		InterfaceResult result = InterfaceResult.getSuccess();
		if (userRegister == null) {
			return InterfaceResult.getErrorResult(ResponseCode.missing_parameter);
		} else if (timestamp == null || StringUtils.isEmpty(sign) || StringUtils.isEmpty(userRegister.getEmail()) || StringUtils.isEmpty(userRegister.getUsername()) || StringUtils.isEmpty(userRegister.getPassword())) {
			return InterfaceResult.getErrorResult(ResponseCode.missing_parameter);
		} else if (!StringHelper.isEmaile(userRegister.getEmail())) {
			return InterfaceResult.getErrorResult(ResponseCode.param_format_error.getCode(),"无效的邮箱地址");
		} else if (!StringUtils.equals(userRegister.getPassword(),userRegister.getRepeatPassword())) {
			return InterfaceResult.getErrorResult(ResponseCode.password_incorrect.getCode(),"两次密码不一致!");
		} else if (comparingTimestamps(timestamp) > 1) {
			return InterfaceResult.getErrorResult(ResponseCode.request_timeout);
		} else if (!StringUtils.equals(sign, sign(userRegister.getUsername(), userRegister.getPassword(), timestamp))) {
			return InterfaceResult.getErrorResult(ResponseCode.sign_error);
		}
		return result;
	}
	
	
	/**
	 * 比较原时间戳和当前时间戳相隔的分钟数
	 * @author laiz
	 * @param timestamps 原时间戳
	 * @return 分钟数
	 */
	public static long comparingTimestamps(Long timestamps) {
		Long currendtimestamps = System.currentTimeMillis();
		return	(currendtimestamps - timestamps) / (1000 * 60);
	}
	
	private static String sign(String username, String password, Long timestamp) {
		return MD5.encryption(username + password + UserConst.LOGIN_KEY + timestamp);
	}
}
