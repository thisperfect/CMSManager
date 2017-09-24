package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;

@Data
public class SysRolePermission {
    private Long id;
    private Long sysPermissionId;
    private Long sysRoleId;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Long createBy;
    private Long updateBy;
}
