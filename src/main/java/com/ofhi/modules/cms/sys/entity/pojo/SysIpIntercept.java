package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;

@Data
public class SysIpIntercept {
    private Long id;
    private Integer isFinal;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Long createBy;
    private Long updateBy;
    private java.util.Date expireTime;
    private String description;
    private String ip;
}
