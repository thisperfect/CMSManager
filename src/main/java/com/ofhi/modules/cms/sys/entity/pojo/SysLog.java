package com.ofhi.modules.cms.sys.entity.pojo;
public class SysLog {
    private Integer id;
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
    public SysLog() {
        super();
    }
    public SysLog(Integer id,String description,String username,Long startTime,Long spendTime,String basePath,String uri,String url,String method,String userAgent,String ip,String permissions,String parameter,String result) {
        super();
        this.id = id;
        this.description = description;
        this.username = username;
        this.startTime = startTime;
        this.spendTime = spendTime;
        this.basePath = basePath;
        this.uri = uri;
        this.url = url;
        this.method = method;
        this.userAgent = userAgent;
        this.ip = ip;
        this.permissions = permissions;
        this.parameter = parameter;
        this.result = result;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getSpendTime() {
        return this.spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPermissions() {
        return this.permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
