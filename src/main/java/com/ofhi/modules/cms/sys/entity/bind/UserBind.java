package com.ofhi.modules.cms.sys.entity.bind;

import com.ofhi.common.user.UserActive;
import com.ofhi.common.user.UserConst;
import com.ofhi.modules.cms.sys.entity.pojo.SysUser;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\9\18 0018
 * Time: 17:39
 */
public class UserBind {

    public static UserActive toActive(SysUser sysUser) {
        UserActive userActive = new UserActive();
        userActive.setAdmin(UserConst.ADMIN.equals(sysUser.getLoginName()));
        userActive.setEmail(sysUser.getEmail());
        userActive.setId(sysUser.getId());
        userActive.setLoginName(sysUser.getLoginName());
        userActive.setPath(sysUser.getPhone());
        userActive.setPhone(sysUser.getPhone());
        return userActive;
    }


}
