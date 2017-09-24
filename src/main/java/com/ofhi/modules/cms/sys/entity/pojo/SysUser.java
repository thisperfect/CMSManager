package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "sys_user")
public class SysUser {

    @Id
    private Long id;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private Integer sex; //1:男  2:女

    @Column(name = "birth")
    private String birth;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "organization_id")
    private String organizationId;

    @Column(name = "password")
    private String password;

    @Column(name = "create_time")
    private java.util.Date createTime;

    @Column(name = "update_time")
    private java.util.Date updateTime;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "status")
    private Integer status = 4;//数据状态,1:正常,2:删除,3:禁用账号,4:未激活

    @Column(name = "is_final")
    private Integer isFinal = 1; //是否能修改,1表示可以修改，2表示不是能修改

    public static SysUser createDefaultObject() {
        SysUser sysUser = new SysUser();
        sysUser.setStatus(null);
        sysUser.setIsFinal(null);
        return sysUser;
    }
}
