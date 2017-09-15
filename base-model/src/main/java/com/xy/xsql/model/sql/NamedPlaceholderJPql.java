package com.xy.xsql.model.sql;

import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/9/15.
 */
public class NamedPlaceholderJPql {
    private String sql;
    private Map<String,Object> args;


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String,Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String,Object> args) {
        this.args = args;
    }


    /**
     * Set SQL
     * @param sql SQL
     * @return This
     */
    public NamedPlaceholderJPql withSql(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * Set c args
     * @param argMap args args
     * @return This
     */
    public NamedPlaceholderJPql withArgs(Map<String,Object> argMap) {
        this.args = argMap;
        return  this;
    }
}
