package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_role")
public class SysRole {

    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "rank")
    private Long rank;  //排序

    @Getter
    @Setter
    @Column(name = "create_time")
    private java.util.Date createTime;

    @Getter
    @Setter
    @Column(name = "update_time")
    private java.util.Date updateTime;

    @Getter
    @Setter
    @Column(name = "create_by")
    private Long createBy;

    @Getter
    @Setter
    @Column(name = "update_by")
    private Long updateBy;
}
