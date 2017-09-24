package com.ofhi.modules.cms.sys.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA
 * Created By laizuan
 * Date: 2017-7-29
 * Time: 9:59
 */
@Getter
@Setter
public class UserRegister {

    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private String validationCode;

    @Override
    public String toString() {
        return String.format("UserRegister{username=%s, email=%s, validationCode=%s}",username, email, validationCode);
    }
}
