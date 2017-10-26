package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;

@Data
public class SysLog {
    private Long id;
    private String description;
    private String username;
    private Long startTime;
    private Long spendTime;
    private String basePath;
    private String uri;
    private String url;
    private String method;
    private String userAgent;
    private String ip;
    private String permissions;
    private String parameter;
    private String result;
    private String module;
    private Enum type;

}
