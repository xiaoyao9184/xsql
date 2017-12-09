package com.xy.xsql.entity.api.dialect.config;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
public class DialectProperties {

    private String type;
    private String typeMapper;
    private boolean notExistThrowError;
    private boolean ignoreNullParam;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeMapper() {
        return typeMapper;
    }

    public void setTypeMapper(String typeMapper) {
        this.typeMapper = typeMapper;
    }

    public boolean isNotExistThrowError() {
        return notExistThrowError;
    }

    public void setNotExistThrowError(boolean notExistThrowError) {
        this.notExistThrowError = notExistThrowError;
    }

    public boolean isIgnoreNullParam() {
        return ignoreNullParam;
    }

    public void setIgnoreNullParam(boolean ignoreNullParam) {
        this.ignoreNullParam = ignoreNullParam;
    }
}
