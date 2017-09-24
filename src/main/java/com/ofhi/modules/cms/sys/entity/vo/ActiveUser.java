package com.ofhi.modules.cms.sys.entity.vo;


import com.ofhi.modules.cms.sys.entity.pojo.SysPermission;
import com.ofhi.modules.cms.sys.entity.pojo.SysRole;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\8\16 0016
 * Time: 13:09
 */
@Data
public class ActiveUser {
    private Long id;
    private String loginName;
    private String name;
    private String email;
    private List<SysRole> roles = Lists.newArrayList();
    private List<SysPermission> permissions = Lists.newArrayList();
}
