package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "sys_role")
public class SysRole {

    @Id
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "rank")
    private Long rank;  //排序

    @Column(name = "create_time")
    private java.util.Date createTime;

    @Column(name = "update_time")
    private java.util.Date updateTime;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "update_by")
    private Long updateBy;
}
