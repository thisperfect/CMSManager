package com.ofhi.modules.cms.sys.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin implements java.io.Serializable {

	@Override
	public String toString() {
		return String.format("UserLogin [username=%s, rememberMe=%s, validationCode=%s", username,rememberMe,validationCode);
	}
	private String username;
	private String password;
	private boolean rememberMe = false;
	private String validationCode;
}
